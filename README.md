# Library Management System

A desktop Java Swing project demonstrating OOP using `Book`, `Member`, `BorrowRecord`, and `Library` classes.

## Requirements
- JDK 8 or newer

## Compile and run (terminal)
```bash
mkdir out
javac -d out src/model/*.java src/service/*.java src/ui/*.java src/App.java
java -cp out App
```

## NetBeans
1. Create a Java Application named `LibraryManagementSystem`.
2. Copy the `src` folders and `App.java` into the project's Source Packages.
3. Run `App.java`.

## Features
- Dashboard counts
- Add, list, search, and remove books
- Add, list, and search members
- Borrow only available books
- Return books and preserve transaction history
- Duplicate and empty-field validation
- ArrayList storage, no database required

Note: Data is in memory and resets when the program closes. This matches the assessment requirement that a database is optional.
