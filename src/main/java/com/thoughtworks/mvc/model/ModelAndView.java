package com.thoughtworks.mvc.model;

public class ModelAndView {
    private ModelMap modelMap;
    private String viewName;

    public ModelAndView(ModelMap modelMap, String viewName) {
        this.modelMap = modelMap;
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public ModelMap getModelMap() {
        return modelMap;
    }
}
