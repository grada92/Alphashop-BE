package io.danielegradassai.service.impl;

import io.danielegradassai.dto.IvaDto;
import io.danielegradassai.entity.Iva;
import io.danielegradassai.repository.IvaRepository;
import io.danielegradassai.service.IvaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class IvaServiceImpl implements IvaService
{
    @Autowired
    IvaRepository ivaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<IvaDto> SelTutti()
    {
        List<Iva> iva =  ivaRepository.findAll();

        List<IvaDto> retVal = iva
                .stream()
                .map(source -> modelMapper.map(source, IvaDto.class))
                .collect(Collectors.toList());

        return retVal;
    }

}
