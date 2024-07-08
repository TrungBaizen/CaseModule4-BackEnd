package com.example.controller;

import com.example.model.Computer;
import com.example.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/computers")
@RestControllerAdvice(basePackageClasses = ComputerController.class)
public class ComputerController {
    private final ComputerService computerService;
    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public ResponseEntity<List<Computer>> getAll() {
        return new ResponseEntity<>(computerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Computer> create(@Validated @RequestBody Computer computer, BindingResult bindingResult) {
        return new ResponseEntity<>(computerService.save(computer,bindingResult), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Computer> update(@Validated @PathVariable Long id,@RequestBody Computer computer,BindingResult bindingResult) {
        return new ResponseEntity<>(computerService.update(computer,id,bindingResult), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Computer> delete(@PathVariable Long id) {
        return new ResponseEntity<>(computerService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputerById(@PathVariable Long id) {
        return new ResponseEntity<>(computerService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Computer>> getComputerByName(@RequestParam String name) {
        return new ResponseEntity<>(computerService.findByName(name), HttpStatus.OK);
    }
}
