package io.danielegradassai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.dto.InfoMsg;
import io.danielegradassai.entity.Articoli;
import io.danielegradassai.exception.BindingException;
import io.danielegradassai.exception.DuplicateException;
import io.danielegradassai.exception.NotFoundException;
import io.danielegradassai.service.ArticoliService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/articoli")
@Log
@CrossOrigin("http://localhost:4200")
public class ArticoliController {

    private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);

    private final ArticoliService articoliService;
    private final ResourceBundleMessageSource resourceBundleMessageSource;
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
    // -------------------------- INSERIMENTO ARTICOLO ---------------------------------
    @PostMapping(value = "/inserisci")
    @SneakyThrows
    public ResponseEntity<InfoMsg> createArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
    {
        log.info("Salviamo l'articolo con codice " + articolo.getCodArt());

        //controllo validit� dati articolo
        if (bindingResult.hasErrors())
        {
            String MsgErr = resourceBundleMessageSource.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            log.warning(MsgErr);

            throw new BindingException(MsgErr);
        }

        //Disabilitare se si vuole gestire anche la modifica
        ArticoliDto checkArt =  articoliService.SelByCodArt(articolo.getCodArt());

        if (checkArt != null)
        {
            String MsgErr = String.format("Articolo %s presente in anagrafica! "
                    + "Impossibile utilizzare il metodo POST", articolo.getCodArt());

            log.warning(MsgErr);

            throw new DuplicateException(MsgErr);
        }

        articoliService.InsArticolo(articolo);

        return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(),
                "Inserimento Articolo Eseguita con successo!"), HttpStatus.CREATED);
    }

    // ------------------- MODIFICA ARTICOLO ------------------------------------
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    @SneakyThrows
    public ResponseEntity<InfoMsg> updateArt(@Valid @RequestBody Articoli articolo, BindingResult bindingResult)
    {
        log.info("Modifichiamo l'articolo con codice " + articolo.getCodArt());

        if (bindingResult.hasErrors())
        {
            String MsgErr = resourceBundleMessageSource.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            log.warning(MsgErr);

            throw new BindingException(MsgErr);
        }

        ArticoliDto checkArt =  articoliService.SelByCodArt(articolo.getCodArt());

        if (checkArt == null)
        {
            String MsgErr = String.format("Articolo %s non presente in anagrafica! "
                    + "Impossibile utilizzare il metodo PUT", articolo.getCodArt());

            log.warning(MsgErr);

            throw new NotFoundException(MsgErr);
        }

        articoliService.InsArticolo(articolo);

        return new ResponseEntity<InfoMsg>(new InfoMsg(LocalDate.now(),
                "Modifica Articolo Eseguita con successo!"), HttpStatus.CREATED);
    }


    // ------------------- ELIMINAZIONE ARTICOLO ------------------------------------
    @DeleteMapping(value = "/elimina/{codart}", produces = "application/json" )
    @SneakyThrows
    public ResponseEntity<?> deleteArt(@PathVariable("codart") String CodArt)
    {
        log.info("Eliminiamo l'articolo con codice " + CodArt);

        Articoli articolo = articoliService.SelByCodArt2(CodArt);

        if (articolo == null)
        {
            String MsgErr = String.format("Articolo %s non presente in anagrafica! ",CodArt);

            log.warning(MsgErr);

            throw new NotFoundException(MsgErr);
        }

        articoliService.DelArticolo(articolo);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Articolo " + CodArt + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }
}
