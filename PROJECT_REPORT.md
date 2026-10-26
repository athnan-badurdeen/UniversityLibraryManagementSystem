# Project Report: Library Management System

## 1. Problem
Manual borrowing records are slow and can allow mistakes. This system lets a librarian manage books, members, borrowing, and returning from one desktop interface.

## 2. Objective
Develop a simple Java Swing application that clearly demonstrates classes, objects, encapsulation, constructors, methods, object relationships, collections, validation, and event handling.

## 3. Classes and relationships
- `Book`: stores book identity, details, and availability.
- `Member`: stores member identity and contact details.
- `BorrowRecord`: associates one `Book` with one `Member`, plus borrow and return dates.
- `Library`: owns ArrayLists and implements business rules.
- `MainFrame`: displays the UI and delegates operations to `Library`.

Relationship: `Library` aggregates many books, members, and records. Each `BorrowRecord` has one book and one member.

## 4. Main workflows
### Add book
Validate fields, reject duplicate ID, create Book, add it to the collection, refresh table.
### Borrow
Select a member and available book, verify availability, set unavailable, create BorrowRecord.
### Return
Select an active record, set return date, mark book available.

## 5. OOP evidence
- Encapsulation: attributes are private and accessed through methods.
- Constructors: initialize valid objects.
- Abstraction: UI calls high-level Library methods.
- Relationships: BorrowRecord links Book and Member.
- Collections: ArrayList stores all objects.
- Separation of concerns: model, service, and UI packages.

## 6. Testing checklist
1. Empty Book ID shows an error.
2. Duplicate Book ID is rejected.
3. Search finds ID, title, author, or category.
4. Borrowing changes status to Borrowed.
5. Borrowed book disappears from available-book list.
6. Returning changes status to Available.
7. Returning the same record twice is rejected.
8. Borrowed book cannot be removed.
9. Dashboard counts update.

## 7. Limitations and future work
In-memory data resets after exit. Future versions can add MySQL, login, edit functions, due dates, fines, reports, and category filters.
