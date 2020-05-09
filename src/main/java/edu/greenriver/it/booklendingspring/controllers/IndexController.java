//David Nagy
// 5/7/20
//indexController.java
// Index controller that controls the routes.




package edu.greenriver.it.booklendingspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author davidnagy
 * @version 1.0
 * Controlling the index route
 *
 */
@Controller
public class IndexController
{
    /**
     * @return index route
     *
     */
    @RequestMapping("/")
    public  String index()
    {
        return "index";
    }


}
