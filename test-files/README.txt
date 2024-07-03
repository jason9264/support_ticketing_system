Overview of test files for WolfTasks

group0.txt - valid file with only a group name
group1.txt - valid file with three categories
group2.txt - valid file with items that can be missing
group3.txt - missing leading ! in file - IAE thrown with message Unable to load file.
group4.txt - category without number of completed tasks - creates group with no categories
group5.txt - category with missing name - creates group with no categories
group6.txt - category with negative completed tasks - creates group with no categories
group7.txt - ticket without a name - creates group with a category - invalid ticket isn't added