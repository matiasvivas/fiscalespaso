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
import java.text.DecimalFormat;
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

        String porcentajeNacionalFormateado = "0.00";
        String porcentajeJovenFormateado = "0.00";
        Integer votosPositivos = 0;
        double porcentajeJoven = 0;
        double porcentajeNacional = 0;
        Integer votantesNacional = 35815436;
        Integer votantesJovenes = 9133475; //entre 16 y 29 años 2021
        votosPositivos = codigosRepository.obtenerTotalVotosPositivos();
        if(votosPositivos>0){
            porcentajeNacional = ((double) votosPositivos * 100) / votantesNacional;
            porcentajeJoven = ((double) votosPositivos * 100) / votantesJovenes;

            DecimalFormat df = new DecimalFormat("#.00"); // Formato para dos decimales
            porcentajeNacionalFormateado = df.format(porcentajeNacional);
            porcentajeJovenFormateado = df.format(porcentajeJoven);
        }
        votos.setPorcentajeJoven(porcentajeJovenFormateado);
        votos.setPositivos(votosPositivos);
        votos.setPorcentajeNacional(porcentajeNacionalFormateado);
        model.addAttribute("votos",votos);
        modelAndView.setViewName("visor/votos");
        return modelAndView;
    }
}
