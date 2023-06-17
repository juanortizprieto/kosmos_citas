package com.kosmos.citas.controller;

import com.kosmos.citas.dto.CitasDTO;
import com.kosmos.citas.dto.ResultDto;
import com.kosmos.citas.model.ConsultoriosEntity;
import com.kosmos.citas.model.DoctoresEntity;
import com.kosmos.citas.service.CitasService;
import com.kosmos.citas.service.ConsultoriosService;
import com.kosmos.citas.service.DoctoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CitasController {
@Autowired
    DoctoresService doctoresService;
@Autowired
    ConsultoriosService consultorioService;

@Autowired
    CitasService citaService;
    @GetMapping("/citaNuevaForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        CitasDTO cita = new CitasDTO();
        List<DoctoresEntity> doctores = doctoresService.findAll();
        List<ConsultoriosEntity> consultorios = consultorioService.findAll();
        model.addAttribute("cita", cita);
        model.addAttribute("doctores",doctores);
        model.addAttribute("consultorios",consultorios);

        return "nueva_cita";
    }

    @PostMapping("/guardarCita")
    public String saveEmployee(@ModelAttribute("cita") CitasDTO cita, Model model) {
        // save employee to database
        ResultDto result = citaService.save(cita);
        if(result.isExito()){
            return "redirect:/";
        }
        else {
            List<DoctoresEntity> doctores = doctoresService.findAll();
            List<ConsultoriosEntity> consultorios = consultorioService.findAll();
            model.addAttribute("cita", cita);
            model.addAttribute("doctores",doctores);
            model.addAttribute("consultorios",consultorios);
            model.addAttribute("errorMessage",result.getMensaje());
            return "nueva_cita";
        }
    }
}
