package io.danielegradassai.service.impl;

import io.danielegradassai.dto.ArticoliDto;
import io.danielegradassai.entity.Articoli;
import io.danielegradassai.repository.ArticoliRepository;
import io.danielegradassai.service.ArticoliService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService {

    private final ArticoliRepository articoliRepository;
    private final ModelMapper modelMapper;
    @Override
    public Iterable<Articoli> SelTutti() {
        return articoliRepository.findAll();
    }

    @Override
    public List<Articoli> SelByDescrizione(String descrizione) {
        return articoliRepository.SelbyDescrizioneLike(descrizione);
    }

    @Override
    public List<Articoli> SelByDescrizione(String descrizione, Pageable pageable) {
        return articoliRepository.findByDescrizioneLike(descrizione, pageable);
    }

    @Override
    public ArticoliDto SelByCodArt(String codArt) {
        Articoli articoli = articoliRepository.findByCodArt(codArt);
        ArticoliDto articoliDto = modelMapper.map(articoli,ArticoliDto.class);

        articoliDto.setUm(articoliDto.getUm().trim());
        articoliDto.setIdStatoArt(articoliDto.getIdStatoArt().trim());
        return articoliDto;
    }

    @Override
    @Transactional
    public void DelArticolo(Articoli articolo) {
        articoliRepository.delete(articolo);
    }

    @Override
    public void InsArticolo(Articoli articolo) {
        articoliRepository.save(articolo);
    }
}
