package ru.decathlon.swagger.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.decathlon.swagger.example.model.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
