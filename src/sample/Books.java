package sample;

import javafx.fxml.Initializable;

public class Books {

    String title, author, category, year, riting;

    public Books(String title, String author, String category, String year, String riting) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.year = year;
        this.riting = riting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRiting() {
        return riting;
    }

    public void setRiting(String riting) {
        this.riting = riting;
    }
}