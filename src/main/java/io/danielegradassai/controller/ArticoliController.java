package io.danielegradassai.controller;

import io.danielegradassai.entity.Articoli;
import io.danielegradassai.entity.Barcode;
import io.danielegradassai.repository.ArticoliRepository;
import io.danielegradassai.service.ArticoliService;
import io.danielegradassai.service.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/articoli")
public class ArticoliController {

    private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

    private final ArticoliService articoliService;
    private final BarcodeService barcodeService;
    @GetMapping(value = "/cerca/ean/{barcode}", produces = "application/json")
    public ResponseEntity<Articoli> listArtByEan(@PathVariable("barcode") String Barcode){
       logger.info("****** Otteniamo l'articolo con barcode " + Barcode + "******");

       Articoli articolo;
       Barcode Ean = barcodeService.SelByBarcode(Barcode);

       if(Ean == null) {
           String ErrMsg = String.format("Il barcode non è stato trovato", Barcode);
           logger.warn(ErrMsg);
           return new ResponseEntity<Articoli>(HttpStatus.NOT_FOUND);
       }
       else {
           articolo = Ean.getArticolo();
       }
       return new ResponseEntity<Articoli>(articolo, HttpStatus.OK);
    }
}
