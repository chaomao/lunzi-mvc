package com.thoughtworks.mvc.action;

import java.util.ArrayList;

public interface ActionCallerFactory {
    ArrayList<ActionCaller> createActionCallers(Iterable<Class> controllerClasses);
}
