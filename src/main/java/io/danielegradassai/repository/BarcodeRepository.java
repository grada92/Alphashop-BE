package io.danielegradassai.repository;

import io.danielegradassai.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, String > {

}
