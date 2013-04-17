package com.thoughtworks.mvc;

import com.thoughtworks.row.ioc.Container;

public class IOCContainer {
    private static Container container;

    private IOCContainer() {
    }

    public static Container getInstance() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }
}
