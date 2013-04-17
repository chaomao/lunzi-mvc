package com.thoughtworks.mvc.parameter.transformers;

import bean.Author;
import bean.SimpleBook;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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
        Author author = transformer.transform(Author.class, "author", map);

        assertThat(author.getName(), is("MaoChao"));
        assertThat(author.getAge(), is(29));
    }

    @Test
    public void should_transform_to_book_which_is_nested() throws Exception {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("simpleBook.author.name", new String[]{"MaoChao"});
        map.put("simpleBook.author.age", new String[]{"29"});
        map.put("simpleBook.name", new String[]{"How to new bee"});
        SimpleBook book = transformer.transform(SimpleBook.class, "simpleBook", map);

        assertThat(book.getName(), is("How to new bee"));
        assertThat(book.getAuthor().getAge(), is(29));
        assertThat(book.getAuthor().getName(), is("MaoChao"));
    }
}
