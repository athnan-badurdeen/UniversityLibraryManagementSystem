package model;

import java.time.LocalDate;

public class BorrowRecord {
    private final String recordId;
    private final Book book;
    private final Member member;
    private final LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(String recordId, Book book, Member member) {
        this.recordId = recordId;
        this.book = book;
        this.member = member;
        this.borrowDate = LocalDate.now();
    }
    public String getRecordId() { return recordId; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isActive() { return returnDate == null; }
    public void markReturned() {
        if (!isActive()) throw new IllegalStateException("This book has already been returned.");
        returnDate = LocalDate.now();
    }
}