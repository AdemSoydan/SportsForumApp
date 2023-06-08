import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }
    }
}

// This will be a singleton class, therefore member class can reach fine field without having its object intance
class ReturnBookResult {
    private double fine;
    private int status;
    public ReturnBookResult(double fine, int status) {
        this.fine = fine;
        this.status = status;
    }
    //GETTER ANS SETTTERS
}
class Book{
    public ReturnBookResult getReturnBookInfo(){
        double fine = computeFine();
        if(fine > 0){
            if (book.hasHold()) {
                return new ReturnBookResult(fine,BOOK_HAS_HOLD_FINE);
            } else {
                return new ReturnBookResult(fine,BOOK_HAS_FINE);
            }
        }
        if (book.hasHold()) {
            return new ReturnBookResult(0,BOOK_HAS_HOLD);
        }
        else{
            return new ReturnBookResult(0,BOOK_HAS_NO_FINE_NO_HOLD);
        }
    }
    private double computeFine(){
        double fine = 0.0;
        if (System.currentTimeMillis() > dueDate.getTimeInMillis()) {
            if (yearApart(acquisitionDate, dueDate)) {
                fine = 0.15 + 0.05 * (daysElapsedSince(dueDate) - 1);
            } else {
                fine = 0.25 + 0.1 * (daysElapsedSince(dueDate) -1 );
            }
            if (hasHold()) {
                fine *= 2;
            }
        }
        return fine;
    }
}

class FineInfo{
    private double fine;
    private int bookStatus;
    private String bookTitle;

    public FineInfo(double fine, int bookStatus, String bookTitle) {
        this.fine = fine;
        this.bookStatus = bookStatus;
        this.bookTitle = bookTitle;
    }
    //GETTER ANS SETTTERS
}

class Member{
    List<Book> borrowedBooks = new ArrayList<>();
    List<FineInfo> fineInfos = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();
    public void returnBook(ReturnBookResult removeBookResult, Book book){
        borrowedBooks.remove(book);
        double fine = removeBookResult.getFine();
        if(fine > 0){
            FineInfo fineInfo = new FineInfo(removeBookResult.getFine(),removeBookResult.getStatus(),book.getTitle());
            fineInfos.add(fineInfo);
        }
        Transanction transanction = new Transaction("Book Returned", book.getTitle());
        transactions.add(transanction);
    }
}


class UserInterface(){
    Text statusText;
    Text fineText;
    public void showInfoAboutBookReturnResult(ReturnBookResult returnBookResult){
        statusText.text = returnBookResult.getStatus();
        fineText.text = returnBookResult.getFine().toString();
    }
}

class Library{
    Book book;
    Member member;

    ReturnBookResult  returnBookResult;
    public Member getBorrower(int bookId){
        book = Catalog.search(bookId);
        member = book.getBorrower();
        return member;
    }

    public void returnBook(){
        book = Catalog.search(bookId);
        returnBookResult = book.getReturnBookInfo();
        UserInterface.showInfoAboutBookReturnResult(returnBookResult);
    }

    public int ClarifyFine(){
        book.returnBook();
        int res = member.returnBook(returnBookResult,book);
        return res;
    }
}