package io.danielegradassai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable; // Import corretto per Pageable

import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.entity.Articoli;
import io.danielegradassai.repository.ArticoliRepository;
import io.danielegradassai.service.ArticoliService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService
{
    @Autowired
    ArticoliRepository articoliRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<Articoli> SelTutti()
    {
        return articoliRepository.findAll();
    }

    @Override
    public List<Articoli> SelByDescrizione(String descrizione, Pageable pageable)
    {
        return articoliRepository.findByDescrizioneLike(descrizione, pageable);
    }

    @Override
    public List<ArticoliDto> SelByDescrizione(String descrizione)
    {
        String filter = "%" + descrizione.toUpperCase() + "%";

        List<Articoli> articoli = articoliRepository.SelByDescrizioneLike(filter);

        List<ArticoliDto> retVal = articoli
                .stream()
                .map(source -> modelMapper.map(source, ArticoliDto.class))
                .collect(Collectors.toList());

        return retVal;
    }

    private ArticoliDto ConvertToDto(Articoli articoli)
    {
        ArticoliDto articoliDto = null;


        if (articoli != null)
        {
            articoliDto =  modelMapper.map(articoli, ArticoliDto.class);
        }

        return articoliDto;
    }

    @Override
    public ArticoliDto SelByBarcode(String barcode)
    {
        Articoli articoli = articoliRepository.SelByEan(barcode);

        return this.ConvertToDto(articoli);

    }

    @Override
    public ArticoliDto SelByCodArt(String codArt)
    {
        Articoli articoli = articoliRepository.findByCodArt(codArt);

        return this.ConvertToDto(articoli);
    }

    @Override
    public Articoli SelByCodArt2(String codArt) {

        return articoliRepository.findByCodArt(codArt);
    }

    @Override
    @Transactional
    public void DelArticolo(Articoli articolo)
    {
        articoliRepository.delete(articolo);
    }

    @Override
    @Transactional
    public void InsArticolo(Articoli articolo)
    {
        articolo.setDescrizione(articolo.getDescrizione().toUpperCase());

        articoliRepository.save(articolo);
    }


}
