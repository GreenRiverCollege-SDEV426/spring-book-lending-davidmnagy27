package edu.greenriver.it.booklendingspring.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController extends AuthenticationInformation implements ErrorController
{
  @RequestMapping("/error")
  public String errorHandler(HttpServletRequest request, Model model)
  {
      String message = "Opps! Something went wrong! We'll get one  of our furry friends on this right away.";
      String reason = "Unkown";

      int status= Integer.valueOf((Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE.toString()));
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
        return "/error";
    }
}
