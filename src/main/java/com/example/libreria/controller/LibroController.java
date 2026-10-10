package com.example.libreria.controller;

import com.example.libreria.model.Libro;
import com.example.libreria.repository.AutorRepository;
import com.example.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorRepository autorRepository;

    // 1. LISTAR TODOS LOS LIBROS
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.listarTodos());
        return "index"; // Busca el archivo index.html
    }

    // 2. MOSTRAR FORMULARIO DE NUEVO LIBRO
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autores", autorRepository.findAll()); // Enviamos los autores disponibles
        return "formulario-libro"; // Busca formulario-libro.html
    }

    // 3. GUARDAR O ACTUALIZAR LIBRO
    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute("libro") Libro libro) {
        libroService.guardar(libro);
        return "redirect:/libros"; // Nos redirige al listado principal
    }

    // 4. MOSTRAR FORMULARIO DE EDICIÓN
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Libro libro = libroService.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("libro", libro);
        model.addAttribute("autores", autorRepository.findAll()); // Cargamos los autores para cambiarlo si se desea
        return "formulario-libro";
    }

    // 5. ELIMINAR LIBRO
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
        return "redirect:/libros";
    }
}
