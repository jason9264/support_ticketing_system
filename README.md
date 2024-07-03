# Project2

## Overview
Project2 is a Java application designed to manage tickets within groups, providing functionalities for creating, editing, and prioritizing tickets across different categories.

## Directory Structure
- `test/`: Directory for test files.
- `test-files/`: Additional test-related files.
- `src/`: Source directory.
  - `edu/ncsu/csc216/wolf_tickets/`
    - `view/gui/`: Contains WolfTicketsGUI.java for the graphical user interface.
    - `model/`
      - `util/`: Utilities for managing lists.
        - `SwapList.java`: Implements swapping operations.
        - `SortedList.java`: Implements sorting operations.
        - `ISwapList.java`: Interface for swap operations.
        - `ISortedList.java`: Interface for sorted list operations.
      - `tickets/`
        - `Ticket.java`: Represents individual tickets.
        - `Category.java`: Represents ticket categories.
        - `ActiveTicketList.java`: Manages active tickets.
        - `AbstractCategory.java`: Abstract class for categories.
    - `io/`
      - `GroupWriter.java`: Writes group data to files.
      - `GroupReader.java`: Reads group data from files.
    - `group/`
      - `Group.java`: Manages groups of tickets.

## Usage
To run the application:
1. Compile and run `WolfTicketsGUI.java`.
2. Use the GUI to interact with ticket groups, categories, and individual tickets.

## Features
- **Adding Groups and Categories**: Easily add new groups and categories for organizing tickets.
- **Loading and Saving Data**: Load existing groups from files and save modifications.
- **Editing and Prioritizing Tickets**: Edit ticket details and prioritize within categories.

## Contributors
- **Jason Wang**
