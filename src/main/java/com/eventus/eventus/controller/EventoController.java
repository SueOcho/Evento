package com.eventus.eventus.controller;

import com.eventus.eventus.model.Cliente;
import com.eventus.eventus.model.Evento;
import com.eventus.eventus.service.ClienteService;
import com.eventus.eventus.service.EventoService;
import com.eventus.eventus.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/crear")
    public String agregarEvento(Model model) {
        model.addAttribute("eventos", new Evento());
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        return "evento/crearEvento";
    }

    @PostMapping("/crear/save")
    public String agregarEventoSave(Evento evento) {
        evento.setCliente(UserDetailsService.cliente);
        eventoService.saveOrUpdate(evento);
        return  "redirect:/evento";

    }

    @GetMapping()
    public String eventos(Model model) {
        eventoService.findAll().ifPresent(eventos -> model.addAttribute("eventos", eventos));
        clienteService.findById(UserDetailsService.cliente.getIdCliente()).ifPresent(cliente -> model.addAttribute("clientes", cliente));
        return "evento/index";
    }


}
