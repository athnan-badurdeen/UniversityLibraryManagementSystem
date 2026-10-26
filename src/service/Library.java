package service;

import model.Book;
import model.Member;
import model.BorrowRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Member> members = new ArrayList<>();
    private final ArrayList<BorrowRecord> records = new ArrayList<>();
    private int nextRecordNumber = 1;

    public void addBook(Book book) {
        requireText(book.getBookId(), "Book ID");
        requireText(book.getTitle(), "Title");
        requireText(book.getAuthor(), "Author");
        requireText(book.getCategory(), "Category");
        if (findBook(book.getBookId()) != null) throw new IllegalArgumentException("Duplicate Book ID.");
        books.add(book);
    }
    public void removeBook(String id) {
        Book book = findBook(id);
        if (book == null) throw new IllegalArgumentException("Book not found.");
        if (!book.isAvailable()) throw new IllegalStateException("A borrowed book cannot be removed.");
        books.remove(book);
    }
    public Book findBook(String id) {
        for (Book b : books) if (b.getBookId().equalsIgnoreCase(id.trim())) return b;
        return null;
    }
    public List<Book> searchBooks(String key) {
        String q = key == null ? "" : key.trim().toLowerCase();
        return books.stream().filter(b -> b.getBookId().toLowerCase().contains(q)
                || b.getTitle().toLowerCase().contains(q)
                || b.getAuthor().toLowerCase().contains(q)
                || b.getCategory().toLowerCase().contains(q)).collect(Collectors.toList());
    }
    public void addMember(Member member) {
        requireText(member.getMemberId(), "Member ID");
        requireText(member.getName(), "Member name");
        requireText(member.getContactNumber(), "Contact number");
        if (findMember(member.getMemberId()) != null) throw new IllegalArgumentException("Duplicate Member ID.");
        members.add(member);
    }
    public Member findMember(String id) {
        for (Member m : members) if (m.getMemberId().equalsIgnoreCase(id.trim())) return m;
        return null;
    }
    public List<Member> searchMembers(String key) {
        String q = key == null ? "" : key.trim().toLowerCase();
        return members.stream().filter(m -> m.getMemberId().toLowerCase().contains(q)
                || m.getName().toLowerCase().contains(q)
                || m.getContactNumber().toLowerCase().contains(q)).collect(Collectors.toList());
    }
    public BorrowRecord borrowBook(Member member, Book book) {
        if (member == null || book == null) throw new IllegalArgumentException("Select both a member and a book.");
        if (!book.isAvailable()) throw new IllegalStateException("Book is already borrowed.");
        book.setAvailable(false);
        BorrowRecord record = new BorrowRecord(String.format("BR%04d", nextRecordNumber++), book, member);
        records.add(record);
        return record;
    }
    public void returnBook(BorrowRecord record) {
        if (record == null) throw new IllegalArgumentException("Select a borrowing record.");
        record.markReturned();
        record.getBook().setAvailable(true);
    }
    public List<Book> getBooks() { return new ArrayList<>(books); }
    public List<Member> getMembers() { return new ArrayList<>(members); }
    public List<BorrowRecord> getRecords() { return new ArrayList<>(records); }
    public List<Book> getAvailableBooks() { return books.stream().filter(Book::isAvailable).collect(Collectors.toList()); }
    public List<BorrowRecord> getActiveRecords() { return records.stream().filter(BorrowRecord::isActive).collect(Collectors.toList()); }
    public long getAvailableCount() { return books.stream().filter(Book::isAvailable).count(); }
    public long getBorrowedCount() { return books.size() - getAvailableCount(); }
    private static void requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(field + " cannot be empty.");
    }
}