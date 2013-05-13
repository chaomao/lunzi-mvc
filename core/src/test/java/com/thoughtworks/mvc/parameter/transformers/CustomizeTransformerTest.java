package com.thoughtworks.mvc.parameter.transformers;

import bean.Author;
import bean.Book;
import bean.SimpleBook;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomizeTransformerTest {

    private CustomizeTransformer transformer;

    @Before
    public void setUp() throws Exception {
        transformer = new CustomizeTransformer();
    }

    @Test
    public void should_transform_to_author() throws Exception {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("author.name", new String[]{"MaoChao"});
        map.put("author.age", new String[]{"29"});
        Author author = (Author) transformer.transform(Author.class, "author", map);

        assertThat(author.getName(), is("MaoChao"));
        assertThat(author.getAge(), is(29));
    }

    @Test
    public void should_transform_to_simple_book_which_is_nested() throws Exception {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("simpleBook.author.name", new String[]{"MaoChao"});
        map.put("simpleBook.author.age", new String[]{"29"});
        map.put("simpleBook.name", new String[]{"How to new bee"});
        SimpleBook book = (SimpleBook) transformer.transform(SimpleBook.class, "simpleBook", map);

        assertThat(book.getName(), is("How to new bee"));
        assertThat(book.getAuthor().getAge(), is(29));
        assertThat(book.getAuthor().getName(), is("MaoChao"));
    }

    @Test
    public void should_transform_to_book_which_contains_list_author() throws Exception {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("book.name", new String[]{"How to new bee"});
        map.put("book.author[].name", new String[]{"Mao", "Chao"});
        map.put("book.author[].age", new String[]{"29", "30"});
        Book book = (Book) transformer.transform(Book.class, "book", map);

        ArrayList<Author> expectAuthors = Lists.newArrayList(
                new Author("Mao", 29),
                new Author("Chao", 30));
        assertThat(book.getName(), is("How to new bee"));
        assertThat(book.getAuthors(), is(expectAuthors));
    }
}
