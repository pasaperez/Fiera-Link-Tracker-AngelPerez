package com.example.fieralinktrackerangelperez.repositories;

import com.example.fieralinktrackerangelperez.models.LinkStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkStorageRepository extends JpaRepository<LinkStorage, Long> {
}