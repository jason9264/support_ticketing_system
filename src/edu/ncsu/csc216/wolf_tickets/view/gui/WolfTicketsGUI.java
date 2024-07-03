package edu.ncsu.csc216.wolf_tickets.view.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.io.GroupReader;
import edu.ncsu.csc216.wolf_tickets.model.tickets.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.tickets.ActiveTicketList;
import edu.ncsu.csc216.wolf_tickets.model.tickets.Ticket;


/**
 * Container for the WolfTickets project that provides the user the ability
 * to interact with groups, ticket lists, and tickets.
 * 
 * @author Dr. Sarah Heckman
 * @author Dr. Sterling McLeod
 */
public class WolfTicketsGUI extends JFrame implements ActionListener {
	
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Title for top of GUI. */
	private static final String APP_TITLE = "WolfTickets";
	/** Text for the File Menu. */
	private static final String FILE_MENU_TITLE = "File";
	/** Text for the New menu item. */
	private static final String NEW_TITLE = "New Group";
	/** Text for the Load menu item. */
	private static final String LOAD_TITLE = "Load Group";
	/** Text for the Save menu item. */
	private static final String SAVE_TITLE = "Save Group";
	/** Text for the Quit menu item. */
	private static final String QUIT_TITLE = "Quit";
	/** Menu bar for the GUI that contains Menus. */
	private JMenuBar menuBar;
	/** Menu for the GUI. */
	private JMenu menu;
	/** Menu item for creating a new group. */
	private JMenuItem itemNew;
	/** Menu item for loading a group file. */
	private JMenuItem itemLoad;
	/** Menu item for saving a group to a file. */
	private JMenuItem itemSave;
	/** Menu item for quitting the program. */
	private JMenuItem itemQuit;
	/** JPanel for the Categories */
	private CategoryPanel pnlCategory;
	/** Border for Group and Categories */
	private TitledBorder groupBorder;
	/** JPanel for Tickets */
	private TicketPanel pnlTicket;
	
	/** Current group - null if no group created. */
	private Group group;
	
	/**
	 * Constructs a WolfTicketsGUI object that will contain a JMenuBar and a
	 * JPanel that will hold different possible views of the data in
	 * the ServiceWolf.
	 */
	public WolfTicketsGUI() {
		super();
		
		//Set up general GUI info
		setSize(1200, 700);
		setLocation(50, 50);
		setTitle(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUpMenuBar();
		
		//Add panel to the container
		Container c = getContentPane();
		c.setLayout(new GridBagLayout());
		
		pnlCategory = new CategoryPanel();
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		groupBorder = BorderFactory.createTitledBorder(lowerEtched, "Group");
		pnlCategory.setBorder(groupBorder);
		pnlCategory.setToolTipText("Group");
		
		pnlTicket = new TicketPanel();
		lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Ticket");
		pnlTicket.setBorder(border);
		pnlTicket.setToolTipText("Ticket");
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = .5;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.fill = GridBagConstraints.BOTH;
		c.add(pnlCategory, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.fill = GridBagConstraints.BOTH;
		c.add(pnlTicket, constraints);
		
		//Set the GUI visible
		setVisible(true);
	}
	
	/**
	 * Makes the GUI Menu bar that contains options working with a file
	 * containing service groups and incidents or for quitting the application.
	 */
	private void setUpMenuBar() {
		//Construct Menu items
		menuBar = new JMenuBar();
		menu = new JMenu(FILE_MENU_TITLE);
		itemNew = new JMenuItem(NEW_TITLE);
		itemLoad = new JMenuItem(LOAD_TITLE);
		itemSave = new JMenuItem(SAVE_TITLE);
		itemQuit = new JMenuItem(QUIT_TITLE);
		itemNew.addActionListener(this);
		itemLoad.addActionListener(this);
		itemSave.addActionListener(this);
		itemQuit.addActionListener(this);
		
		//Start with save button disabled
		itemSave.setEnabled(false);
		
		//Build Menu and add to GUI
		menu.add(itemNew);
		menu.add(itemLoad);
		menu.add(itemSave);
		menu.add(itemQuit);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itemNew) {
			if (group != null && group.isChanged()) {
				int select = JOptionPane.showConfirmDialog(null, "Current Group is unsaved. Would you like to save before creating a new Group?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (select == 1) {
					promptForGroupName();
				} 
			} else {
				promptForGroupName();
			}
			
		} else if (e.getSource() == itemLoad) {
			try {
				if (group != null && group.isChanged()) {
					int select = JOptionPane.showConfirmDialog(null, "Current Group is unsaved. Would you like to save before creating a new Group?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (select == 1) {
						group = GroupReader.readGroupFile(new File(getFileName(true)));
						pnlCategory.updateCategorys();
					}
				} else {
					group = GroupReader.readGroupFile(new File(getFileName(true)));
					pnlCategory.updateCategorys();
				}
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, "Unable to load file.");
			} catch (IllegalStateException ise) {
				//ignore the exception
			}
		} else if (e.getSource() == itemSave) {
			//Save current service group and incidents
			try {
				group.saveGroup(new File(getFileName(false)));
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, "Unable to save file.");
			} catch (IllegalStateException exp) {
				//Don't do anything - user canceled (or error)
			}
		} else if (e.getSource() == itemQuit) {
			if (group != null && group.isChanged()) {
				//Quit the program
				try {
					group.saveGroup(new File(getFileName(false)));
					System.exit(0);  //Ignore SpotBugs warning here - this is the only place to quit the program!
				} catch (IllegalArgumentException exp) {
					JOptionPane.showMessageDialog(this, "Unable to save file.");
				} catch (IllegalStateException exp) {
					//Don't do anything - user canceled (or error)
				}
			} else {
				System.exit(0);
			}
		}
		if (group != null) {
			groupBorder.setTitle("Group: " + group.getGroupName());
		}
		
		pnlCategory.updateCategorys();
		
		itemSave.setEnabled(group != null && group.isChanged());
		repaint();
		validate();
		
	}
	
	/**
	 * Prompts the user for the group name.
	 */
	private void promptForGroupName() {
		String groupName = (String) JOptionPane.showInputDialog(this, "Group Name?");
		if (groupName == null) {
			return; //no need to do anything
		}
		group = new Group(groupName);
	}
	
	/**
	 * Returns a file name generated through interactions with a JFileChooser
	 * object.
	 * @param load true if loading a file, false if saving
	 * @return the file name selected through JFileChooser
	 * @throws IllegalStateException if no file name provided
	 */
	private String getFileName(boolean load) {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChooser to current working directory
		int returnVal = Integer.MIN_VALUE;
		if (load) {
			returnVal = fc.showOpenDialog(this);
		} else {
			returnVal = fc.showSaveDialog(this);
		}
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			//Error or user canceled, either way no file name.
			throw new IllegalStateException();
		}
		File gameFile = fc.getSelectedFile();
		return gameFile.getAbsolutePath();
	}
	
	/**
	 * Starts the GUI for the WolfTickets application.
	 * @param args command line arguments
	 */
	public static void main(String [] args) {
		new WolfTicketsGUI();
	}
	
	/**
	 * JPanel for Category.
	 */
	private class CategoryPanel extends JPanel implements ActionListener {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		
		/** Leading text for completes tickets label */
		private static final String COMPLETED_TASKS_LABEL = "Number of Completed Tickets: ";
		
		/** Label for selecting current category */
		private JLabel lblCurrentCategory;
		/** Combo box for category list */
		private JComboBox<String> comboCategorys;
		/** Label for number of completed tickets */
		private JLabel lblCompletedTickets;
		/** Button to add a category */
		private JButton btnAddCategory;
		/** Button to edit the selected category */
		private JButton btnEditCategory;
		/** Button to remove the selected category */
		private JButton btnRemoveCategory;

		/** JTable for displaying the list of tickets */
		private JTable tableTickets;
		/** TableModel for Tickets */
		private TicketTableModel tableModel;
		
		/**
		 * Creates the categories and displays the ticket list for the current category.
		 */
		public CategoryPanel() {
			super(new GridBagLayout());
			
			lblCurrentCategory = new JLabel("Current Category");
			comboCategorys = new JComboBox<String>();
			comboCategorys.addActionListener(this);
			
			lblCompletedTickets = new JLabel(COMPLETED_TASKS_LABEL);
			
			btnAddCategory = new JButton("Add Category");
			btnEditCategory = new JButton("Edit Category");
			btnRemoveCategory = new JButton("Remove Category");
			
			btnAddCategory.addActionListener(this);
			btnEditCategory.addActionListener(this);
			btnRemoveCategory.addActionListener(this);
			
			tableModel = new TicketTableModel();
			tableTickets = new JTable(tableModel);
			tableTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableTickets.setPreferredScrollableViewportSize(new Dimension(500, 500));
			tableTickets.setFillsViewportHeight(true);
			tableTickets.getColumnModel().getColumn(0).setPreferredWidth(5);
			tableTickets.getColumnModel().getColumn(1).setPreferredWidth(300);
			tableTickets.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					int idx = tableTickets.getSelectedRow();
					pnlTicket.setTicket(idx);
				}
				
			});
			
			JScrollPane listScrollPane = new JScrollPane(tableTickets);
			
			JPanel pnlActiveTicketList = new JPanel();
			pnlActiveTicketList.setLayout(new GridLayout(1, 2));
			pnlActiveTicketList.add(lblCurrentCategory);
			pnlActiveTicketList.add(comboCategorys);
			
			JPanel pnlCategoryActions = new JPanel();
			pnlCategoryActions.setLayout(new GridLayout(1, 3));
			pnlCategoryActions.add(btnAddCategory);
			pnlCategoryActions.add(btnEditCategory);
			pnlCategoryActions.add(btnRemoveCategory);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(pnlActiveTicketList, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(lblCompletedTickets, c);
			
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(pnlCategoryActions, c);
			
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 1;
			c.weighty = 20;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			c.gridheight = GridBagConstraints.REMAINDER;
			c.gridwidth = GridBagConstraints.REMAINDER;
			add(listScrollPane, c);
			
			updateCategorys();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == comboCategorys) {
				int idx = comboCategorys.getSelectedIndex();
				
				if (idx == -1) {
					updateCategorys();
				} else {
					String categoryName = comboCategorys.getItemAt(idx);
					group.setCurrentCategory(categoryName);
					updateCategorys();
				}
			} else if (e.getSource() == btnAddCategory) {
				try {
					String categoryName = (String) JOptionPane.showInputDialog(this, "Category Name?", "Create New Category", JOptionPane.QUESTION_MESSAGE);
					group.addCategory(new edu.ncsu.csc216.wolf_tickets.model.tickets.Category(categoryName, 0));
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				}
			} else if (e.getSource() == btnEditCategory) {
				try {
					if (group.getCurrentCategory() instanceof ActiveTicketList) {
						JOptionPane.showMessageDialog(WolfTicketsGUI.this, "The Active Tickets list may not be edited.");
					} else {
						String categoryName = (String) JOptionPane.showInputDialog(this, "Update Category Name Name?", "Edit Category", JOptionPane.QUESTION_MESSAGE, null, null, group.getCurrentCategory().getCategoryName());
						group.editCategory(categoryName);
					}
					
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				}
			} else if (e.getSource() == btnRemoveCategory) {
				try {
					if (group.getCurrentCategory() instanceof ActiveTicketList) {
						JOptionPane.showMessageDialog(WolfTicketsGUI.this, "The Active Tickets list may not be deleted.");
					} else {
						int selection = JOptionPane.showOptionDialog(this, "Are you sure you want to delete " + group.getCurrentCategory().getCategoryName() + "?", "Delete Category", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (selection == 0) { //Yes
							group.removeCategory();
						}
					}
					
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				}
			}
			updateCategorys();
			WolfTicketsGUI.this.repaint();
			WolfTicketsGUI.this.validate();
		}
		
		public void updateCategorys() {
			if (group == null) {
				btnAddCategory.setEnabled(false);
				btnEditCategory.setEnabled(false);
				btnRemoveCategory.setEnabled(false);

			} else {
				AbstractCategory category = group.getCurrentCategory();
				
				String categoryName = category.getCategoryName();
				btnAddCategory.setEnabled(true);
				btnEditCategory.setEnabled(true);
				btnRemoveCategory.setEnabled(true);
				
				comboCategorys.removeAllItems();
				String [] categoryNames = group.getCategoriesNames();
				for (int i = 0; i < categoryNames.length; i++) {
					comboCategorys.addItem(categoryNames[i]);
				}
				
				comboCategorys.setSelectedItem(categoryName);
				
				if (comboCategorys.getSelectedIndex() == 0) { //active tickets
					pnlTicket.enableButtons(false);
					pnlTicket.btnComplete.setEnabled(true);
				} else {
					pnlTicket.enableButtons(true);
				}
				
				lblCompletedTickets.setText(COMPLETED_TASKS_LABEL + category.getCompletedCount());
			}
			
			itemSave.setEnabled(group != null && group.isChanged());
			tableModel.updateData();
		}
		
		/**
		 * TicketTableModel is the object underlying the JTable object that displays
		 * the list of Tickets to the user.
		 */
		private class TicketTableModel extends AbstractTableModel {
			
			/** ID number used for object serialization. */
			private static final long serialVersionUID = 1L;
			/** Column names for the table */
			private String [] columnNames = {"Category", "Ticket Title"};
			/** Data stored in the table */
			private Object [][] data;
			
			/**
			 * Constructs the IncidentTableModel by requesting the latest information
			 * from the IncidentTableModel.
			 */
			public TicketTableModel() {
				updateData();
			}

			/**
			 * Returns the number of columns in the table.
			 * @return the number of columns in the table.
			 */
			public int getColumnCount() {
				return columnNames.length;
			}

			/**
			 * Returns the number of rows in the table.
			 * @return the number of rows in the table.
			 */
			public int getRowCount() {
				if (data == null) 
					return 0;
				return data.length;
			}
			
			/**
			 * Returns the column name at the given index.
			 * @param col the column index
			 * @return the column name at the given column.
			 */
			public String getColumnName(int col) {
				return columnNames[col];
			}

			/**
			 * Returns the data at the given {row, col} index.
			 * @param row the row index
			 * @param col the column index
			 * @return the data at the given location.
			 */
			public Object getValueAt(int row, int col) {
				if (data == null)
					return null;
				return data[row][col];
			}
			
			/**
			 * Sets the given value to the given {row, col} location.
			 * @param value Object to modify in the data.
			 * @param row the row index
			 * @param col the column index
			 */
			public void setValueAt(Object value, int row, int col) {
				data[row][col] = value;
				fireTableCellUpdated(row, col);
			}
			
			/**
			 * Updates the given model with Tickets information from a Category.
			 */
			private void updateData() {
				if (group != null) {
					AbstractCategory currentCategory = group.getCurrentCategory();
					data = currentCategory.getTicketsAsArray();
				}
			}
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for interacting with a Ticket.
	 */
	private class TicketPanel extends JPanel implements ActionListener {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		
		/** Label - ticket name */
		private JLabel lblTicketName;
		/** TextField - ticket name */
		private JTextField txtTicketName;
		
		/** Label - active */
		private JLabel lblActive;
		/** Checkbox - active */
		private JCheckBox checkActive;
		
		/** Label - description */
		private JLabel lblDescription;
		/** Text Area - description */
		private JTextArea txtDescription;
		
		/** Button - add/edit */
		private JButton btnAddEdit;
		/** Button - remove */
		private JButton btnRemove;
		/** Button - complete */
		private JButton btnComplete;
		/** Button - clear */
		private JButton btnClear;
		
		/** Button - move up */
		private JButton btnMoveUp;
		/** Button - move down */
		private JButton btnMoveDown;
		/** Button - move to front */
		private JButton btnMoveToFront;
		/** Button - move to back */
		private JButton btnMoveToBack;
		
		public TicketPanel() {
			super(new GridBagLayout());
			
			lblTicketName = new JLabel("Ticket Name");
			txtTicketName = new JTextField(25);
			
			lblActive = new JLabel("Active");
			checkActive = new JCheckBox();
					
			lblDescription = new JLabel("Description");
			txtDescription = new JTextArea(10, 50);
			
			btnAddEdit = new JButton("Add / Edit");
			btnRemove = new JButton("Remove");
			btnComplete = new JButton("Complete Ticket");
			btnClear = new JButton("Clear Ticket Information");
			
			btnMoveUp = new JButton("Move Up");
			btnMoveDown = new JButton("Move Down");
			btnMoveToFront = new JButton("Move to Front");
			btnMoveToBack = new JButton("Move to Back");
			
			btnAddEdit.addActionListener(this);
			btnRemove.addActionListener(this);
			btnComplete.addActionListener(this);
			btnClear.addActionListener(this);
			
			btnMoveUp.addActionListener(this);
			btnMoveDown.addActionListener(this);
			btnMoveToFront.addActionListener(this);
			btnMoveToBack.addActionListener(this);
			
			enableButtons(false);
			
			JScrollPane scrollDescription = new JScrollPane(txtDescription);
			scrollDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			GridBagConstraints c = new GridBagConstraints();
			
			//Row 1 - name
			JPanel row1 = new JPanel();
			row1.setLayout(new GridLayout(2, 1));
			row1.add(lblTicketName);
			row1.add(txtTicketName);
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(row1, c);
			
			//Row 2 - active
			JPanel row2 = new JPanel();
			row2.setLayout(new GridLayout(1, 4));
			row2.add(lblActive);
			row2.add(checkActive);
						
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(row2, c);
			
			//Row 3 - description
			JPanel row3 = new JPanel();
			row3.setLayout(new GridLayout(2, 1));
			row3.add(lblDescription);
			row3.add(scrollDescription);
			
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 2;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(row3, c);
			
			//Row 4 - add/edit/remove
			JPanel row4 = new JPanel();
			row4.setLayout(new GridLayout(1, 2));
			row4.add(btnAddEdit);
			row4.add(btnRemove);
			
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(row4, c);
			
			//Row 5 - complete/clear
			JPanel row5 = new JPanel();
			row5.setLayout(new GridLayout(1, 2));
			row5.add(btnComplete);
			row5.add(btnClear);
			
			c.gridx = 0;
			c.gridy = 4;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(row5, c);
			
			//Row 6 - move up/down
			JPanel row6 = new JPanel();
			row6.setLayout(new GridLayout(1, 2));
			row6.add(btnMoveUp);
			row6.add(btnMoveDown);
			
			c.gridx = 0;
			c.gridy = 5;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(row6, c);
			
			//Row 7 - move front/back
			JPanel row7 = new JPanel();
			row7.setLayout(new GridLayout(1, 2));
			row7.add(btnMoveToFront);
			row7.add(btnMoveToBack);
			
			c.gridx = 0;
			c.gridy = 6;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(row7, c);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int idx = pnlCategory.tableTickets.getSelectedRow();
			
			if (e.getSource() == btnAddEdit) {
				try {
					if (idx == -1) {
						Ticket t = new Ticket(txtTicketName.getText(), txtDescription.getText(), checkActive.isSelected());
						group.addTicket(t);
					} else {
						group.editTicket(idx, txtTicketName.getText(), txtDescription.getText(), checkActive.isSelected());
					}
					setTicket(-1);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				}
			} else if (e.getSource() == btnRemove) {
				try {
					group.getCurrentCategory().removeTicket(idx);
					setTicket(-1);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, "No ticket selected.");
				}
			} else if (e.getSource() == btnComplete) {
				try {
					Ticket t = group.getCurrentCategory().getTicket(idx);
					t.completeTicket();
					setTicket(-1);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, "No ticket selected.");
				}
			} else if (e.getSource() == btnClear) {
				try {
					setTicket(-1);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, "No ticket selected.");
				}
			} else if (e.getSource() == btnMoveUp) {
				try {
					group.getCurrentCategory().getTickets().moveUp(idx);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, "No ticket selected.");
				}
			} else if (e.getSource() == btnMoveDown) {
				try {
					group.getCurrentCategory().getTickets().moveDown(idx);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, "No ticket selected.");
				}
			} else if (e.getSource() == btnMoveToFront) {
				try {
					group.getCurrentCategory().getTickets().moveToFront(idx);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, "No ticket selected.");
				}
			} else if (e.getSource() == btnMoveToBack) {
				try {
					group.getCurrentCategory().getTickets().moveToBack(idx);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, iae.getMessage());
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(WolfTicketsGUI.this, "No ticket selected.");
				}
			} 
			itemSave.setEnabled(group != null && group.isChanged());
			pnlCategory.updateCategorys();
		}
		
		/**
		 * Sets the information for the selected ticket and enables the buttons.
		 * @param idx index of selected ticket
		 */
		public void setTicket(int idx) {
			try {
				Ticket t = group.getCurrentCategory().getTicket(idx);
				
				txtTicketName.setText(t.getTicketName());
				txtDescription.setText(t.getTicketDescription());
				
				checkActive.setSelected(t.isActive());
				
			} catch (IndexOutOfBoundsException e) {
				txtTicketName.setText("");
				txtDescription.setText("");
				checkActive.setSelected(false);
				
				pnlCategory.tableTickets.getSelectionModel().clearSelection();
				
				enableButtons(false);
			}
		}
		
		/**
		 * Enable or disable all buttons 
		 * @param enable true if enable, false if disabled
		 */
		public void enableButtons(boolean enable) {
			btnAddEdit.setEnabled(enable); 
			btnRemove.setEnabled(enable);
			btnComplete.setEnabled(enable);
			btnClear.setEnabled(enable);
			
			btnMoveUp.setEnabled(enable);
			btnMoveDown.setEnabled(enable);
			btnMoveToFront.setEnabled(enable);
			btnMoveToBack.setEnabled(enable);
		}
		
	}

}