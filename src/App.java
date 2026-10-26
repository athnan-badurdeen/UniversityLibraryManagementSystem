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
            library.addBook(new Book("B001", "Clean Code", "Robert C. Martin", "Programming"));
            library.addBook(new Book("B002", "Java: The Complete Reference", "Herbert Schildt", "Programming"));
            library.addMember(new Member("M001", "John", "0712345678"));
            library.addMember(new Member("M002", "Ravi", "0711632637"));
            library.addMember(new Member("M002", "Hari", "0714878236"));
            new MainFrame(library).setVisible(true);
        });
    }
}
