package com.yangtt.hj.okhttptest.Data;

/**
 * Created by hj on 2017/12/19.
 */
import java.math.BigDecimal;
public class Book {
    private String name;
    private String author;
    private BigDecimal price;
    public Book(String name,String author,BigDecimal price){
        this.name=name;
        this.author=author;
        this.price=price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
