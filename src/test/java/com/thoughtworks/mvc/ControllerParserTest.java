package com.thoughtworks.mvc;

import controllers.DummyController;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ControllerParserTest {

    @Test
    public void should_parse_all_controllers_under_specific_controller_folder() {
        ControllerParser controllerParser = new ControllerParser();
        ArrayList<Class> controllers = controllerParser.parse("src/test/java/controllers");
        assertThat(controllers.size(), is(1));
        assertThat(controllers, hasItem(DummyController.class));
    }
}
