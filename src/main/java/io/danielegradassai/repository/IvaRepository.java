package io.danielegradassai.repository;

import io.danielegradassai.entity.Iva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IvaRepository  extends JpaRepository<Iva, Integer>
{

}