package io.danielegradassai.repository;

import io.danielegradassai.entity.FamAssort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<FamAssort, Integer>
{

}
