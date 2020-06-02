//David Nagy
//6/1/2020
//CustomErrorController.java
// error notifier route



package edu.greenriver.it.booklendingspring.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author davidnagy
 * @version 5.0
 */
@Controller
public class CustomErrorController extends AuthenticationInformation implements ErrorController
{
    /**
     * @param request errors if wrong
     * @param model errors
     * @return error
     */
  @RequestMapping("/error")
  public String errorHandler(HttpServletRequest request, Model model)
  {
      String message = "Opps! Something went wrong! We'll get one  of our furry friends on this right away.";
      String reason = "Unkown";

      int status= (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
      if(status == HttpStatus.NOT_FOUND.value())
      {
          reason = "(page not found)";

      }
      else if(status == HttpStatus.INTERNAL_SERVER_ERROR.value())
      {
          reason="server error";
      }
      model.addAttribute("message",message);
      model.addAttribute("reason",reason);
      return "/general/error";
  }

    @Override
    public String getErrorPath() {
        return null;
    }
}
