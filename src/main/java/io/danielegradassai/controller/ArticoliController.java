package io.danielegradassai.controller;

import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.entity.Articoli;
import io.danielegradassai.entity.Barcode;
import io.danielegradassai.exception.NotFoundException;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/articoli")
public class ArticoliController {

    private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

    private final ArticoliService articoliService;
    private final BarcodeService barcodeService;
    @GetMapping(value = "/cerca/ean/{barcode}", produces = "application/json")
    public ResponseEntity<Articoli> listArtByEan(@PathVariable("barcode") String Barcode) throws NotFoundException {
       logger.info("****** Otteniamo l'articolo con barcode " + Barcode + "******");

       Articoli articolo;
       Barcode Ean = barcodeService.SelByBarcode(Barcode);

       if(Ean == null) {
           String ErrMsg = String.format("Il barcode non è stato trovato", Barcode);
           logger.warn(ErrMsg);
           throw new NotFoundException(ErrMsg);
          // return new ResponseEntity<Articoli>(HttpStatus.NOT_FOUND);
       }
       else {
           articolo = Ean.getArticolo();
       }
       return new ResponseEntity<Articoli>(articolo, HttpStatus.OK);
    }

    @GetMapping(value = "/cerca/codice/{codart}",produces = "application/json")
    public ResponseEntity<ArticoliDto> listArtByCodArt(@PathVariable("codart") String CodArt) throws NotFoundException{
        logger.info("****** Otteniamo l'articolo con codice + ", CodArt);

        ArticoliDto articolo = articoliService.SelByCodArt(CodArt);

        if(articolo == null){
            String ErrMsg = String.format("L'articolo non è stato trovato", CodArt);
            logger.warn(ErrMsg);
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<ArticoliDto>(articolo, HttpStatus.OK);
    }
    @GetMapping(value = "/cerca/descrizione/{filter}", produces = "application/json")
    public ResponseEntity<List<ArticoliDto>> listArtByDesc(@PathVariable("filter") String Filter)
            throws NotFoundException
    {
        logger.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");

        List<ArticoliDto> articoli = articoliService.SelByDescrizione(Filter.toUpperCase() + "%");

        if (articoli == null)
        {
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente descrizione %s", Filter);

            logger.warn(ErrMsg);

            throw new NotFoundException(ErrMsg);

        }

        return new ResponseEntity<List<ArticoliDto>>(articoli, HttpStatus.OK);
    }
}
