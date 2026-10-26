package ui;

import model.*;
import service.Library;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final Library library;
    private final JLabel totalBooks = new JLabel();
    private final JLabel availableBooks = new JLabel();
    private final JLabel borrowedBooks = new JLabel();
    private final JLabel totalMembers = new JLabel();

    public MainFrame(Library library) {
        this.library = library;
        setTitle("University Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 600);
        setLocationRelativeTo(null);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Dashboard", dashboardPanel());
        tabs.addTab("Books", booksPanel());
        tabs.addTab("Members", membersPanel());
        tabs.addTab("Borrow", borrowPanel());
        tabs.addTab("Return / History", returnPanel());
        add(tabs);
        tabs.addChangeListener(e -> { refreshDashboard(); refreshCurrentTab(tabs); });
        refreshDashboard();
    }

    private JPanel dashboardPanel() {
        JPanel p = new JPanel(new GridLayout(2, 2, 20, 20));
        p.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));
        p.add(card("Total Books", totalBooks)); p.add(card("Available Books", availableBooks));
        p.add(card("Borrowed Books", borrowedBooks)); p.add(card("Members", totalMembers));
        return p;
    }
    private JPanel card(String title, JLabel value) {
        JPanel p = new JPanel(new BorderLayout()); p.setBorder(BorderFactory.createTitledBorder(title));
        value.setHorizontalAlignment(SwingConstants.CENTER); value.setFont(new Font("SansSerif", Font.BOLD, 32)); p.add(value); return p;
    }
    private JPanel booksPanel() {
        JPanel root = new JPanel(new BorderLayout(8, 8)); root.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextField id=new JTextField(), title=new JTextField(), author=new JTextField(), category=new JTextField(), search=new JTextField();
        JPanel form=new JPanel(new GridLayout(2,4,6,6));
        form.add(labeled("Book ID",id)); form.add(labeled("Title",title)); form.add(labeled("Author",author)); form.add(labeled("Category",category));
        JButton add=new JButton("Add Book"), remove=new JButton("Remove Selected"), find=new JButton("Search"), showAll=new JButton("Show All");
        JPanel actions=new JPanel(); actions.add(add); actions.add(remove); actions.add(new JLabel("Search:")); search.setPreferredSize(new Dimension(180,26)); actions.add(search); actions.add(find); actions.add(showAll);
        String[] cols={"Book ID","Title","Author","Category","Status"}; DefaultTableModel m=tableModel(cols); JTable table=new JTable(m);
        Runnable all=()->loadBooks(m,library.getBooks()); add.addActionListener(e->runAction(()->{library.addBook(new Book(id.getText().trim(),title.getText().trim(),author.getText().trim(),category.getText().trim())); clear(id,title,author,category); all.run();},"Book added."));
        remove.addActionListener(e->runAction(()->{int r=table.getSelectedRow(); if(r<0)throw new IllegalArgumentException("Select a book."); library.removeBook(m.getValueAt(r,0).toString()); all.run();},"Book removed."));
        find.addActionListener(e->loadBooks(m,library.searchBooks(search.getText()))); showAll.addActionListener(e->all.run()); all.run();
        JPanel north=new JPanel(new BorderLayout()); north.add(form,BorderLayout.CENTER); north.add(actions,BorderLayout.SOUTH); root.add(north,BorderLayout.NORTH); root.add(new JScrollPane(table)); return root;
    }
    private JPanel membersPanel() {
        JPanel root=new JPanel(new BorderLayout(8,8)); root.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextField id=new JTextField(),name=new JTextField(),contact=new JTextField(),search=new JTextField(); JPanel form=new JPanel(new GridLayout(1,3,6,6));
        form.add(labeled("Member ID",id)); form.add(labeled("Name",name)); form.add(labeled("Contact Number",contact));
        JButton add=new JButton("Add Member"),find=new JButton("Search"),showAll=new JButton("Show All"); JPanel actions=new JPanel(); actions.add(add); actions.add(new JLabel("Search:")); search.setPreferredSize(new Dimension(180,26)); actions.add(search); actions.add(find); actions.add(showAll);
        DefaultTableModel m=tableModel(new String[]{"Member ID","Name","Contact Number"}); JTable table=new JTable(m); Runnable all=()->loadMembers(m,library.getMembers());
        add.addActionListener(e->runAction(()->{library.addMember(new Member(id.getText().trim(),name.getText().trim(),contact.getText().trim())); clear(id,name,contact); all.run();},"Member added.")); find.addActionListener(e->loadMembers(m,library.searchMembers(search.getText()))); showAll.addActionListener(e->all.run()); all.run();
        JPanel north=new JPanel(new BorderLayout()); north.add(form); north.add(actions,BorderLayout.SOUTH); root.add(north,BorderLayout.NORTH); root.add(new JScrollPane(table)); return root;
    }
    private JPanel borrowPanel() {
        JPanel root=new JPanel(new GridBagLayout()); JComboBox<Member> members=new JComboBox<>(); JComboBox<Book> books=new JComboBox<>(); JButton refresh=new JButton("Refresh Lists"), borrow=new JButton("Borrow Book");
        GridBagConstraints g=new GridBagConstraints(); g.insets=new Insets(8,8,8,8); g.fill=GridBagConstraints.HORIZONTAL; g.gridx=0;g.gridy=0;root.add(new JLabel("Member:"),g);g.gridx=1;root.add(members,g);g.gridx=0;g.gridy=1;root.add(new JLabel("Available Book:"),g);g.gridx=1;root.add(books,g);g.gridx=0;g.gridy=2;root.add(refresh,g);g.gridx=1;root.add(borrow,g);
        Runnable load=()->{members.removeAllItems(); library.getMembers().forEach(members::addItem); books.removeAllItems(); library.getAvailableBooks().forEach(books::addItem);}; refresh.addActionListener(e->load.run()); borrow.addActionListener(e->runAction(()->{library.borrowBook((Member)members.getSelectedItem(),(Book)books.getSelectedItem());load.run();},"Book borrowed successfully.")); load.run(); root.putClientProperty("refresh",load); return root;
    }
    private JPanel returnPanel() {
        JPanel root=new JPanel(new BorderLayout(8,8)); root.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); DefaultTableModel m=tableModel(new String[]{"Record ID","Book","Member","Borrow Date","Return Date","Status"}); JTable table=new JTable(m); JButton refresh=new JButton("Refresh"),ret=new JButton("Return Selected Book"); JPanel actions=new JPanel(); actions.add(refresh);actions.add(ret);root.add(actions,BorderLayout.NORTH);root.add(new JScrollPane(table)); Runnable load=()->loadRecords(m); refresh.addActionListener(e->load.run()); ret.addActionListener(e->runAction(()->{int r=table.getSelectedRow();if(r<0)throw new IllegalArgumentException("Select a record.");String id=m.getValueAt(r,0).toString();BorrowRecord found=null;for(BorrowRecord x:library.getRecords())if(x.getRecordId().equals(id))found=x;library.returnBook(found);load.run();},"Book returned successfully.")); load.run(); root.putClientProperty("refresh",load); return root;
    }
    private JPanel labeled(String text,JTextField f){JPanel p=new JPanel(new BorderLayout());p.add(new JLabel(text),BorderLayout.NORTH);p.add(f);return p;}
    private DefaultTableModel tableModel(String[] cols){return new DefaultTableModel(cols,0){public boolean isCellEditable(int r,int c){return false;}};}
    private void loadBooks(DefaultTableModel m,List<Book> list){m.setRowCount(0);for(Book b:list)m.addRow(new Object[]{b.getBookId(),b.getTitle(),b.getAuthor(),b.getCategory(),b.getStatus()});}
    private void loadMembers(DefaultTableModel m,List<Member> list){m.setRowCount(0);for(Member x:list)m.addRow(new Object[]{x.getMemberId(),x.getName(),x.getContactNumber()});}
    private void loadRecords(DefaultTableModel m){m.setRowCount(0);for(BorrowRecord r:library.getRecords())m.addRow(new Object[]{r.getRecordId(),r.getBook(),r.getMember(),r.getBorrowDate(),r.getReturnDate()==null?"-":r.getReturnDate(),r.isActive()?"Borrowed":"Returned"});}
    private void clear(JTextField...fs){for(JTextField f:fs)f.setText("");}
    private void runAction(Runnable r,String success){try{r.run();refreshDashboard();JOptionPane.showMessageDialog(this,success);}catch(Exception ex){JOptionPane.showMessageDialog(this,ex.getMessage(),"Cannot complete operation",JOptionPane.ERROR_MESSAGE);}}
    private void refreshDashboard(){totalBooks.setText(String.valueOf(library.getBooks().size()));availableBooks.setText(String.valueOf(library.getAvailableCount()));borrowedBooks.setText(String.valueOf(library.getBorrowedCount()));totalMembers.setText(String.valueOf(library.getMembers().size()));}
    private void refreshCurrentTab(JTabbedPane tabs){Component c=tabs.getSelectedComponent();if(c instanceof JPanel){Object r=((JPanel)c).getClientProperty("refresh");if(r instanceof Runnable)((Runnable)r).run();}}
}