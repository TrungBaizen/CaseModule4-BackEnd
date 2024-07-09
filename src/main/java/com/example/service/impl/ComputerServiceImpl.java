package com.example.service.impl;

import com.example.controller.ExceptionController;
import com.example.model.Category;
import com.example.model.Computer;
import com.example.repository.ComputerRepository;
import com.example.service.ComputerService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public Computer save(Computer computer, BindingResult bindingResult) {
        List<String> errors = ExceptionController.getMessageError(bindingResult);
        if (computerRepository.existsComputerByName(computer.getName())) {
            errors.add("name: Tên đã tồn tại");
        }
        if (errors.size() > 0) {
            throw new ValidationException(errors.stream().collect(Collectors.joining("; ")));
        }
        return computerRepository.save(computer);
    }

    @Override
    public Computer update(Computer computer, Long id, BindingResult bindingResult) {
        findById(id);
        List<String> errors = ExceptionController.getMessageError(bindingResult);
        if (computerRepository.existsComputerByName(computer.getName())) {
            errors.add("name: Tên đã tồn tại");
        }
        if (errors.size() > 0) {
            throw new ValidationException(errors.stream().collect(Collectors.joining("; ")));
        }
        computer.setId(id);
        return computerRepository.save(computer);
    }

    @Override
    public Computer findById(Long id) {
        Optional<Computer> computer = computerRepository.findById(id);
        if (computer.isPresent()) {
            return computerRepository.findById(id).get();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Computer delete(Long id) {
        Computer computer = findById(id);
        computerRepository.delete(computer);
        return computer;
    }

    @Override
    public List<Computer> findAll() {
        if (computerRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return computerRepository.findAll();
    }

    @Override
    public List<Computer> findByName(String name) {
        if (computerRepository.findByNameContaining(name).isEmpty()) {
            throw new IllegalArgumentException();
        }
        return computerRepository.findByNameContaining(name);
    }
}
