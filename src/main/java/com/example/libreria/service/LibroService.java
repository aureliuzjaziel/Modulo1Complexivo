package com.example.libreria.service;

import com.example.libreria.model.Libro;
import com.example.libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // 1. LEER (Obtener todos los libros)
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    // 2. BUSCAR (Obtener un libro por su ID)
    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    // 3. GUARDAR o ACTUALIZAR
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    // 4. ELIMINAR
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
}
