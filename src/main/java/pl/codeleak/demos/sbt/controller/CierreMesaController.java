package pl.codeleak.demos.sbt.controller;

import javax.validation.Valid;

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
import pl.codeleak.demos.sbt.repository.CierreMesaRepository;
import pl.codeleak.demos.sbt.service.UserService;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Controller
@RequestMapping(path = "/cierremesa")
public class CierreMesaController {

    @Autowired
    UserService userService;
    @Autowired
    CierreMesaRepository cierreMesaRepository;
    @GetMapping(value = "/")
    public ModelAndView cargadorInicial(Model model) {

        model.addAttribute("cierremesa", new CierreMesa());

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);
        modelAndView.setViewName("fiscal/cerrar_mesa");
        return modelAndView;
    }

    @GetMapping(value = "/agregar")
    public String agregar(Model model) {
        model.addAttribute("cierremesa", new CierreMesa());
        model.addAttribute("distritos", Distritos.values());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);

        //Permite solo un cierre de mesa
        if(cierreMesaRepository.obtenerMisCierresCargados(user.getUserName())!=null
        && !cierreMesaRepository.obtenerMisCierresCargados(user.getUserName()).isEmpty()){
            model.addAttribute("yaCerro", true);
        }
        else{
            model.addAttribute("yaCerro", false);
        }

        return "cierremesa/agregar";
    }

    @GetMapping(value = "/mostrar")
    public String mostrar(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);

        model.addAttribute("cierremesas", cierreMesaRepository.obtenerMisCierresCargados(user.getUserName()));
        model.addAttribute("distritos", Distritos.values());

        return "fiscal/ver";
    }

    @PostMapping(value = "/agregar")
    public String guardar(Model model, @ModelAttribute @Valid CierreMesa cierremesa,
                          @RequestParam("imageFile") MultipartFile imageFile, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            return "cierremesa/agregar";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("userName", user.getName() + " " + user.getLastName());

        String role = "";
        for (Role roleT : user.getRoles()){
            role = roleT.getRole();
        }
        model.addAttribute("role", role);

        cierremesa.setUsername(user.getUserName());
        cierremesa.setFechaHoraCierre(new Date());

        if (!imageFile.isEmpty()) {
            try {
                byte[] imageBytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                cierremesa.setFotoBase64(base64Image); // Asegúrate de tener el setter adecuado en tu entidad CierreMesa
            } catch (IOException e) {
                e.printStackTrace();
                // Manejo de error si ocurre un problema al leer la imagen
            }
        }

        cierreMesaRepository.save(cierremesa);
        redirectAttrs
            .addFlashAttribute("mensaje", "Agregado correctamente")
            .addFlashAttribute("clase", "success");
        return "redirect:/cierremesa/mostrar";
    }
}
