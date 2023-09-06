package io.danielegradassai.service.impl;

import io.danielegradassai.entity.Articoli;
import io.danielegradassai.entity.Barcode;
import io.danielegradassai.repository.BarcodeRepository;
import io.danielegradassai.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BarcodeServiceImpl implements BarcodeService {

    private final BarcodeRepository barcodeRepository;
    @Override
    public Barcode SelByBarcode(String Barcode) {

        Barcode barcode = barcodeRepository.findByBarCode(Barcode);
        if(Barcode != null) {
            Articoli articolo = barcode.getArticolo();
            articolo.setUm(articolo.getUm().trim());
            articolo.setIdStatoArt(articolo.getIdStatoArt().trim());

            barcode.setArticolo(articolo);
        }
        return barcode;
    }
}
