package org.springweb.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class FormController extends SimpleFormController{
	
    public FormController(){
        this.setCommandClass(Student.class);
    }

    protected ModelAndView onSubmit(Object object, BindException arg1) throws Exception {
         Student stu=(Student)object;
         System.out.println(stu);
         return new ModelAndView(getSuccessView(),"student",stu);
    }

}

