package io.danielegradassai.service.impl;

import org.springframework.data.domain.Pageable; // Import corretto per Pageable

import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.entity.Articoli;
import io.danielegradassai.repository.ArticoliRepository;
import io.danielegradassai.service.ArticoliService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService {

    private final ArticoliRepository articoliRepository;
    private final ModelMapper modelMapper;
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
        List<Articoli> articoli = articoliRepository.SelByDescrizioneLike(descrizione);

        articoli.forEach(e -> e.setIdStatoArt(e.getIdStatoArt().trim()));
        articoli.forEach(e -> e.setUm(e.getUm().trim()));

        List<ArticoliDto> val = articoli
                .stream()
                .map(source -> modelMapper.map(source, ArticoliDto.class))
                .collect(Collectors.toList());

        return val;
    }

    @Override
    public ArticoliDto SelByCodArt(String codArt)
    {
        Articoli articoli = articoliRepository.findByCodArt(codArt);
        ArticoliDto articoliDto = null;

        if (articoli != null)
        {
            articoliDto =  modelMapper.map(articoli, ArticoliDto.class);

            articoliDto.setUm(articoliDto.getUm().trim());
            articoliDto.setIdStatoArt(articoliDto.getIdStatoArt().trim());
        }

        return articoliDto;
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
        articoliRepository.save(articolo);
    }
}
