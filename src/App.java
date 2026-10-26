import model.Book;
import model.Member;
import service.Library;
import ui.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ignored) {}
            Library library = new Library();
            // books
            library.addBook(new Book("B001", "Clean Code", "Robert C. Martin", "Programming"));
            library.addBook(new Book("B002", "Java: The Complete Reference", "Herbert Schildt", "Programming"));
            library.addBook(new Book("B003", "Effective Java", "Joshua Bloch", "Programming"));
            library.addBook(new Book("B004", "Head First Java", "Kathy Sierra", "Programming"));
            library.addBook(new Book("B005", "Introduction to Algorithms", "Thomas H. Cormen", "Computer Science"));
            library.addBook(new Book("B006", "Design Patterns", "Erich Gamma", "Software Engineering"));
            library.addBook(new Book("B007", "The Pragmatic Programmer", "Andrew Hunt", "Programming"));
            library.addBook(new Book("B008", "Python Crash Course", "Eric Matthes", "Programming"));
            library.addBook(new Book("B009", "Learning Python", "Mark Lutz", "Programming"));
            library.addBook(new Book("B010", "Computer Networks", "Andrew S. Tanenbaum", "Networking"));
            library.addBook(new Book("B011", "Database System Concepts", "Abraham Silberschatz", "Database"));
            library.addBook(new Book("B012", "Operating System Concepts", "Abraham Silberschatz", "Operating Systems"));
            library.addBook(new Book("B013", "Artificial Intelligence: A Modern Approach", "Stuart Russell", "Artificial Intelligence"));
            library.addBook(new Book("B014", "Data Structures and Algorithms in Java", "Robert Lafore", "Programming"));
            library.addBook(new Book("B015", "Computer Organization and Design", "David A. Patterson", "Computer Architecture"));
            library.addBook(new Book("B016", "Software Engineering", "Ian Sommerville", "Software Engineering"));
            library.addBook(new Book("B017", "Web Design with HTML, CSS, JavaScript and jQuery", "Jon Duckett", "Web Development"));
            library.addBook(new Book("B018", "Eloquent JavaScript", "Marijn Haverbeke", "Web Development"));
            library.addBook(new Book("B019", "Spring in Action", "Craig Walls", "Programming"));
            library.addBook(new Book("B020", "Android Programming: The Big Nerd Ranch Guide", "Bill Phillips", "Mobile Development"));
            library.addBook(new Book("B021", "Hands-On Machine Learning", "Aurelien Geron", "Machine Learning"));
            library.addBook(new Book("B022", "Cybersecurity Essentials", "Charles J. Brooks", "Cybersecurity"));
            // members
            library.addMember(new Member("M001", "John", "0712345678"));
            library.addMember(new Member("M002", "Ravi", "0711632637"));
            library.addMember(new Member("M003", "Hari", "0714878236"));
            library.addMember(new Member("M004", "Ahamed", "0701000004"));
            library.addMember(new Member("M005", "Nimal", "0701000005"));
            library.addMember(new Member("M006", "Kamal", "0701000006"));
            library.addMember(new Member("M007", "Saman", "0701000007"));
            library.addMember(new Member("M008", "Kasun", "0701000008"));
            library.addMember(new Member("M009", "Tharindu", "0701000009"));
            library.addMember(new Member("M010", "Dilshan", "0701000010"));
            library.addMember(new Member("M011", "Isuru", "0701000011"));
            library.addMember(new Member("M012", "Lahiru", "0701000012"));
            library.addMember(new Member("M013", "Chamara", "0701000013"));
            library.addMember(new Member("M014", "Lakshan", "0701000014"));
            library.addMember(new Member("M015", "Supun", "0701000015"));
            library.addMember(new Member("M016", "Ruwan", "0701000016"));
            library.addMember(new Member("M017", "Dinesh", "0701000017"));
            library.addMember(new Member("M018", "Arun", "0701000018"));
            library.addMember(new Member("M019", "Karthik", "0701000019"));
            library.addMember(new Member("M020", "Vijay", "0701000020"));
            library.addMember(new Member("M021", "Praveen", "0701000021"));
            library.addMember(new Member("M022", "Ashan", "0701000022"));
            library.addMember(new Member("M023", "Shehan", "0701000023"));
            library.addMember(new Member("M024", "Fathima", "0701000024"));
            library.addMember(new Member("M025", "Nirosha", "0701000025"));
            library.addMember(new Member("M026", "Kavindi", "0701000026"));
            library.addMember(new Member("M027", "Tharushi", "0701000027"));
            library.addMember(new Member("M028", "Ayesha", "0701000028"));
            library.addMember(new Member("M029", "Shalini", "0701000029"));
            library.addMember(new Member("M030", "Divya", "0701000030"));
            library.addMember(new Member("M031", "Sajani", "0701000031"));
            library.addMember(new Member("M032", "Imran", "0701000032"));
            library.addMember(new Member("M033", "Naveen", "0701000033"));
            new MainFrame(library).setVisible(true);
        });
    }
}
