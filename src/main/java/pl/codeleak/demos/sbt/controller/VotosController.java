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
import pl.codeleak.demos.sbt.model.Votos;
import pl.codeleak.demos.sbt.repository.CodigosRepository;
import pl.codeleak.demos.sbt.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping(path = "/votos")
public class VotosController {

    @Autowired
    UserService userService;
    @Autowired
    CodigosRepository codigosRepository;
    @GetMapping(value = "/")
    public ModelAndView cargadorInicial(Model model) {

        model.addAttribute("votos", new Votos());

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);

        Votos votos = new Votos();
        Integer votosPositivos = 0;
        Integer porcentajeJoven = 0;
        Integer porcentajeNacional = 0;
        Integer votantesNacional = 35815436;
        Integer votantesJovenes = 9133475; //entre 16 y 29 años 2021
        votosPositivos = codigosRepository.obtenerTotalVotosPositivos();
        if(votosPositivos>0){
            porcentajeNacional = (votosPositivos*100)/votantesNacional;
            porcentajeJoven = (votosPositivos*100)/votantesJovenes;
        }
        votos.setPorcentajeJoven(porcentajeJoven);
        votos.setPositivos(votosPositivos);
        votos.setPorcentajeNacional(votantesNacional);
        model.addAttribute("votos",votos);
        modelAndView.setViewName("visor/votos");
        return modelAndView;
    }
}
