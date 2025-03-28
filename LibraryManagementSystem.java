package librarymanagementsystem;

import java.util.*;

class Book 
{

    private String title;
    private String author;
    private String isbn;
    private String depart;
    private boolean is_Issued;

    public Book(String title, String author, String isbn, String depart) 
    {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.depart = depart;
        this.is_Issued = false;
    }

    public String getAuthor()
    {
        return author.toLowerCase();
    }

    public String getTitle() 
    {
        return title.toLowerCase();
    }

    public String getIsbn()
    {
        return isbn;
    }

    public boolean isIssued()
    {
        return is_Issued;
    }

    public String getDepart() 
    {
        return depart.toLowerCase();
    }

    public void issueBook() 
    {
        is_Issued = true;
    }

    public void returnBook()
    {
        is_Issued = false;
    }

    @Override
    public String toString()
    {
        return "Book [Title= " + title + ", Author= " + author + ", ISBN= " + isbn + ", Depart= " + depart + ", Issued= " + (is_Issued ? "Yes" : "No") + "]";
    }
}

class User 
{

    private String name;
    private String id;
    private List<Book> issuedBooks;

    public User(String name, String id)
    {
        this.name = name;
        this.id = id;
        this.issuedBooks = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public List<Book> getIssuedBooks() 
    {
        return issuedBooks;
    }

    public void issueBook(Book book)
    {
        issuedBooks.add(book);
    }

    public void returnBook(Book book)
    {
        issuedBooks.remove(book);
    }

    @Override
    public String toString() 
    {
        return "User [Name= " + name + ", ID= " + id + "]";
    }
}

class Library
{

    private Map<String, Book> books;  // ISBN as a key
    private Map<String, User> users;  // User ID as a key

    public Library() 
    {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    public void addBook(String title, String author, String isbn, String depart)
    {
        if (!books.containsKey(isbn)) 
        {
            books.put(isbn, new Book(title, author, isbn, depart));
            System.out.println("The Book " + title + " added.");
        } 
        
        else 
        {
            System.out.println("Book with ISBN " + isbn + " is already exists.");
        }
    }

    public void deleteBook(String isbn)
    {
        if (books.containsKey(isbn))
        {
            books.remove(isbn);
            System.out.println("The Book is removed successfully.");
        } 
        
        else
        {
            System.out.println("The Book isn't found.");
        }
    }

    public void modifyBook(String newTitle, String newAuthor, String isbn, String newDepart) 
    {
        Book book = books.get(isbn);
        if (book != null) 
        {
            books.put(isbn, new Book(newTitle, newAuthor, isbn, newDepart));
            System.out.println("The Book " + newTitle + " is updated successfully.");
        }
        
        else
        {
            System.out.println("ٍSorry, This Book isn't found.");
        }
    }

    public void registerUser(String name, String id)
    {
        if (!users.containsKey(id))
        {
            users.put(id, new User(name, id));
            System.out.println("User registered: " + name);
        } 
        
        else
        {
            System.out.println("User with ID " + id + " is already exists.");
        }
    }

    public List<Book> searchBookByTitle(String title) 
    {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books.values()) 
        {
            if (book.getTitle().equalsIgnoreCase(title)) 
            {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchBookByِAuthor(String author) 
    {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books.values())
        {
            if (book.getAuthor().equalsIgnoreCase(author)) 
            {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> searchBookByِDepart(String depart)
    {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books.values()) 
        {
            if (book.getDepart().equalsIgnoreCase(depart)) 
            {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public Book searchBookByISBN(String isbn)
    {
        return books.get(isbn);
    }

    public void issueBookToUser(String userId, String isbn) 
    {
        User user = users.get(userId);
        Book book = books.get(isbn);

        if (user == null) 
        {
            System.out.println("User isn't found.");
            return;
        }

        if (book == null)
        {
            System.out.println("Book isn't found.");
            return;
        }

        if (book.isIssued()) 
        {
            System.out.println("ٍSorry, This Book is already issued.");
            return;
        }

        if (userId == user.getId()) 
        {
            book.issueBook();
            user.issueBook(book);
            System.out.println("Book issued to " + user.getName() + ": " + book.getTitle());
        }
        
        else
        {
            System.out.println("Sorry, This is an invalid ID.");
        }
    }

    public void returnBookFromUser(String userId, String isbn) 
    {
        User user = users.get(userId);
        Book book = books.get(isbn);

        if (user == null)
        {
            System.out.println("User isn't found.");
            return;
        }

        if (book == null) 
        {
            System.out.println("Book isn't found.");
            return;
        }

        if (!book.isIssued()) 
        {
            System.out.println("Book wasn't issued.");
            return;
        }

        if (userId == user.getId()) 
        {
            book.returnBook();
            user.returnBook(book);
            System.out.println("Book returned by " + user.getName() + ": " + book.getTitle());
        } 
        
        else 
        {
            System.out.println("Sorry, This is an invalid ID.");
        }
    }

    public void displayAllBooks()
    {
        if (books.isEmpty()) 
        {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books.values()) 
        {
            System.out.println(book);
        }
    }

    public void displayIssuedBooks(String userId) 
    {
        User user = users.get(userId);

        if (user == null) 
        {
            System.out.println("User isn't found.");
            return;
        }

        List<Book> issuedBooks = user.getIssuedBooks();

        if (issuedBooks.isEmpty())
        {
            System.out.println(user.getName() + " hasn't issued any books.");
        } 
        
        else 
        {
            System.out.println(user.getName() + "'s issued books: ");

            for (Book book : issuedBooks) 
            {
                System.out.println(book);
            }
        }
    }

    public void displayAllUsers() 
    {
        if (users.isEmpty()) 
        {
            System.out.println("No users registered.");
            return;
        }

        for (User user : users.values())
        {
            System.out.println(user);
        }
    }

    public void displayLibraryStatus() 
    {
        int issuedCount = 0;
        for (Book book : books.values()) 
        {
            if (book.isIssued()) 
            {
                issuedCount++;
            }
        }
        
        System.out.println("Total Books: " + books.size());
        System.out.println("Issued Books: " + issuedCount);
    }

    public void Search() 
    {
        System.out.println("1. Search by Title.");
        System.out.println("2. Search by Author.");
        System.out.println("3. Search by ISBN.");
        System.out.println("4. Search by Department.");
        System.out.println("5. return to the main menu.");

        Scanner cin = new Scanner(System.in);
        int choice;

        while (true) 
        {
            try 
            {
                choice = Integer.parseInt(cin.nextLine());
                break;
            } 
            
            catch (NumberFormatException e) 
            {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        switch (choice) 
        {
            case 1:
                System.out.println("Enter the title of the book.");
                String title = cin.next().toLowerCase();
                List<Book> bookBytitle = searchBookByTitle(title);
                if (bookBytitle != null)
                {
                    System.out.println(bookBytitle);
                } 
                else
                {
                    System.out.println("Book not found.");
                }
                break;

            case 2:
                System.out.println("Enter the author of the book.");
                String author = cin.next().toLowerCase();
                List<Book> bookByauthor = searchBookByِAuthor(author);
                if (bookByauthor != null) 
                {
                    System.out.println(bookByauthor);
                }
                else
                {
                    System.out.println("Book not found.");
                }
                break;

            case 3:
                System.out.println("Enter the ISBN of the book.");
                String isbn = cin.next();
                Book bookByisbn = searchBookByISBN(isbn);
                if (bookByisbn != null) 
                {
                    System.out.println(bookByisbn);
                }
                else
                {
                    System.out.println("Book not found.");
                }
                break;

            case 4:
                System.out.println("Enter the department that the book is belong to.");
                String depart = cin.next().toLowerCase();
                List<Book> bookBydepart = searchBookByِDepart(depart);
                if (bookBydepart != null) 
                {
                    System.out.println(bookBydepart);
                }
                else
                {
                    System.out.println("Book not found.");
                }
                break;

            case 5:
                System.out.println("Returning to the main menu...");
                return;

            default:
                System.out.println("Invalid option. Please try again.");
                Search();
        }
    }
}

public class LibraryManagementSystem 
{
    
    public static void main(String[] args) 
    {
        Scanner cin = new Scanner(System.in);
        Library library = new Library();

        while (true) 
        {
            System.out.println("\n                                    Library Management System");
            System.out.println("                                 *******************************");
            System.out.println("1. Add a Book");
            System.out.println("2. Delete a Book");
            System.out.println("3. Modify a Book");
            System.out.println("4. Register a User");
            System.out.println("5. Search about a Book");
            System.out.println("6. Issue Book to User");
            System.out.println("7. Return Book");
            System.out.println("8. Display All Books");
            System.out.println("9. Display Issued Books for User");
            System.out.println("10. Display All Users");
            System.out.println("11. Display Library Status");
            System.out.println("12. Exit");

            System.out.println("\nPlease, Enter your choice.");
            int choice;

            while (true) 
            {
                try
                {
                    choice = Integer.parseInt(cin.nextLine());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = cin.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = cin.nextLine();
                    System.out.print("Enter Book ISBN: ");
                    String isbn = cin.nextLine();
                    System.out.print("Enter Book Department: ");
                    String depart = cin.nextLine();
                    library.addBook(title, author, isbn, depart);
                    break;

                case 2:
                    System.out.print("Enter Book ISBN: ");
                    String Isbn = cin.nextLine();
                    library.deleteBook(Isbn);
                    break;

                case 3:
                    System.out.print("Enter The newBook Title: ");
                    String newTitle = cin.nextLine();
                    System.out.print("Enter The new Book Author: ");
                    String newAuthor = cin.nextLine();
                    System.out.print("Enter The Book ISBN: ");
                    String newIsbn = cin.nextLine();
                    System.out.print("Enter The new Book Department: ");
                    String newDepart = cin.nextLine();
                    library.modifyBook(newTitle, newAuthor, newIsbn, newDepart);
                    break;

                case 4:
                    System.out.print("Enter User Name: ");
                    String name = cin.nextLine();
                    System.out.print("Enter User ID: ");
                    String id = cin.nextLine();
                    library.registerUser(name, id);
                    break;

                case 5:
                    library.Search();
                    break;

                case 6:
                    System.out.print("Enter User ID: ");
                    id = cin.nextLine();
                    System.out.print("Enter Book ISBN to Issue: ");
                    isbn = cin.nextLine();
                    library.issueBookToUser(id, isbn);
                    break;

                case 7:
                    System.out.print("Enter User ID: ");
                    id = cin.nextLine();
                    System.out.print("Enter Book ISBN to Return: ");
                    isbn = cin.nextLine();
                    library.returnBookFromUser(id, isbn);
                    break;

                case 8:
                    library.displayAllBooks();
                    break;

                case 9:
                    System.out.print("Enter User ID to Display Issued Books: ");
                    id = cin.nextLine();
                    library.displayIssuedBooks(id);
                    break;

                case 10:
                    library.displayAllUsers();
                    break;

                case 11:
                    library.displayLibraryStatus();
                    break;

                case 12:
                    System.out.println("Exiting...");
                    cin.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
