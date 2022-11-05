package ru.decathlon.swagger.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.decathlon.swagger.example.model.Cat;
import ru.decathlon.swagger.example.repository.CatRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CatService {

    private final CatRepository repository;

    @Autowired
    public CatService(CatRepository repository) {
        this.repository = repository;
    }

    public List<Cat> getCats() {
        return repository.findAll();
    }

    public Cat getCatById(Long catId) {
        return repository.findById(catId)
                .orElseThrow(NoSuchElementException::new);
    }

    public void saveCat(Cat cat) {
        repository.save(cat);
    }

    public void deleteCatById(Long catId) {
        var cat = repository.findById(catId)
                .orElseThrow(NoSuchElementException::new);
        repository.delete(cat);
    }
}