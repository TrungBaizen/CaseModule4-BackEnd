package com.example.service.impl;

import com.example.model.Computer;
import com.example.repository.ComputerRepository;
import com.example.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
    private ComputerRepository computerRepository;
    @Autowired
    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }
    @Override
    public Computer save(Computer computer) {
        return computerRepository.save(computer);
    }

    @Override
    public Computer update(Computer computer, Long id) {
        computer.setId(id);
        return computerRepository.save(computer);
    }

    @Override
    public Computer findById(Long id) {
        return computerRepository.findById(id).get();
    }

    @Override
    public Computer delete(Long id) {
        Computer computer = findById(id);
        computerRepository.delete(computer);
        return computer;
    }

    @Override
    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    @Override
    public List<Computer> findByName(String name) {
        return computerRepository.findByName(name);
    }
}
