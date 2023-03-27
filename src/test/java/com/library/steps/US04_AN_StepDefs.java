package com.library.steps;


import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import io.cucumber.java.en.*;
import org.junit.Assert;


public class US04_AN_StepDefs {
    BookPage bookPage = new BookPage();
    String bookName;

    @Given("the {string} on the home page AN")
    public void the_on_the_home_pageAN(String userType) {
        new LoginPage().login(userType);
    }

    @When("the user navigates to {string} page AN")
    public void the_user_navigates_to_pageAN(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
    }

    @When("the user searches for {string} book AN")
    public void the_user_searches_for_bookAN(String bookName) {
        this.bookName = bookName;
        bookPage.search.sendKeys(bookName);
    }

    @When("the user clicks edit book button AN")
    public void the_user_clicks_edit_book_buttonAN() {
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 3);
        bookPage.editBook(bookName).click();
    }

    @Then("book information must match the Database AN")
    public void book_information_must_match_the_databaseAN() {
        DB_Util.runQuery("select * from books where name='"+bookName+"'");
        BrowserUtil.waitForClickablility(bookPage.saveChanges,5);
        Assert.assertEquals(DB_Util.getCellValue(1, 2), bookPage.bookName.getAttribute("value"));
        Assert.assertEquals(DB_Util.getCellValue(1, 3), bookPage.isbn.getAttribute("value"));
        Assert.assertEquals(DB_Util.getCellValue(1, 4), bookPage.year.getAttribute("value"));
        Assert.assertEquals(DB_Util.getCellValue(1, 5), bookPage.author.getAttribute("value"));
    }

}