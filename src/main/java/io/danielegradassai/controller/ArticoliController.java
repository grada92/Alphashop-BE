package io.danielegradassai.controller;

import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.entity.Articoli;
import io.danielegradassai.exception.NotFoundException;
import io.danielegradassai.service.ArticoliService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/articoli")
@Log
@CrossOrigin("http://localhost:4200")
public class ArticoliController {

    private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

    private final ArticoliService articoliService;
    @GetMapping(value = "/cerca/barcode/{ean}", produces = "application/json")
    public ResponseEntity<ArticoliDto> listArtByEan(@PathVariable("barcode") String Ean)
            throws NotFoundException
    {
        log.info("****** Otteniamo l'articolo con barcode " + Ean + " *******");

        ArticoliDto articolo = articoliService.SelByBarcode(Ean);

        if (articolo == null)
        {
            String ErrMsg = String.format("Il barcode %s non è stato trovato!", Ean);

            log.warning(ErrMsg);

            throw new NotFoundException(ErrMsg);

            //return new ResponseEntity<Articoli>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ArticoliDto>(articolo, HttpStatus.OK);

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
        log.info("****** Otteniamo gli articoli con Descrizione: " + Filter + " *******");

        List<ArticoliDto> articoli = articoliService.SelByDescrizione(Filter.toUpperCase() + "%");

        if (articoli.isEmpty())
        {
            String ErrMsg = String.format("Non è stato trovato alcun articolo avente descrizione %s", Filter);

            log.warning(ErrMsg);

            throw new NotFoundException(ErrMsg);

        }

        return new ResponseEntity<List<ArticoliDto>>(articoli, HttpStatus.OK);
    }

    public ResponseEntity<Articoli> createArt(@Valid @RequestBody Articoli articolo){

    }
}
