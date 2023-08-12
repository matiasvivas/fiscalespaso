package pl.codeleak.demos.sbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.codeleak.demos.sbt.enumeradores.Distritos;
import pl.codeleak.demos.sbt.model.CierreMesa;
import pl.codeleak.demos.sbt.model.Role;
import pl.codeleak.demos.sbt.model.User;
import pl.codeleak.demos.sbt.repository.CodigosRepository;
import pl.codeleak.demos.sbt.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping(path = "/boletavalida")
public class BoletaValidaController {

    @Autowired
    UserService userService;
    @Autowired
    CodigosRepository codigosRepository;
    @GetMapping(value = "/")
    public ModelAndView cargadorInicial(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);
        modelAndView.setViewName("fiscal/voleta_valida");
        return modelAndView;
    }
}
