package ru.hogwarts.chool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.chool.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {
}