package com.java26.eolt.service;

import com.java26.eolt.dto.EoltDto;
import com.java26.eolt.dto.VariantDto;
import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.entity.VariantEntity;
import com.java26.eolt.repository.EoltRepository;
import com.java26.eolt.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class EoltService {

    final EoltRepository eoltRepository;
    final VariantRepository variantRepository;

    public List<EoltDto> findAll() {
        log.info("EoltService:findAll");
        List<EoltEntity> eoltEntities = eoltRepository.findAll();
        List<EoltDto> eoltDtos = new ArrayList<>();
        for (EoltEntity eoltEnt : eoltEntities) {
            EoltDto eoltDto = new EoltDto();
            eoltDto.setEoltName(eoltEnt.getEoltName());
            eoltDto.setLocation(eoltEnt.getLocation());
            eoltDtos.add(eoltDto);
        }
        return eoltDtos;
    }

    public EoltDto findByName(String eoltName) {
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        EoltDto eoltDto = new EoltDto();
        eoltDto.setEoltName(eoltEntity.getEoltName());
        eoltDto.setLocation(eoltEntity.getLocation());
        return eoltDto;
    }

    public void create(EoltDto eoltDto) {
        log.info("EoltService:create");
        EoltEntity eoltEntity = new EoltEntity();
        eoltEntity.setEoltName(eoltDto.getEoltName());
        eoltEntity.setLocation(eoltDto.getLocation());
        eoltRepository.save(eoltEntity);
    }

    @Transactional
    public void update(EoltDto eoltDto) {
        log.info("EoltService:update");
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltDto.getEoltName())
                .orElseThrow(() -> new EntityNotFoundException(eoltDto.getEoltName()));
        eoltRepository.setLocationForEoltEntity(eoltDto.getLocation(), eoltEntity.getId());
        log.info("");
    }

    public void delete(String eoltName) {
        log.info("EoltService:delete eoltNAme");
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        eoltRepository.deleteById(eoltEntity.getId());
    }


    public List<VariantDto> findAllVariants(String eoltName) {
        log.info("EoltService:findAllVariants");
        List<VariantDto> variantDtos = new ArrayList<>();
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        List<VariantEntity> variantEntities = variantRepository.findByEolt(eoltEntity);
        for (VariantEntity variantEntity : variantEntities) {
            VariantDto variantDto = new VariantDto();
            variantDto.setDpn(variantEntity.getDpn());
            variantDtos.add(variantDto);
        }
        return variantDtos;
    }

    public List<EoltDto> findAllEoltForVariant(String variant) {
        List<EoltDto> eoltDtos = new ArrayList<>();
        EoltEntity eoltEntity;
        List<EoltEntity> eoltEntities = new ArrayList<>();
        List<VariantEntity> variantEntities = variantRepository.findByDpn(variant);
        for (VariantEntity variantEntity : variantEntities) {
            eoltEntity = variantEntity.getEolt();
            eoltEntities.add(eoltEntity);
        }
        for (EoltEntity eoltEntity1 : eoltEntities) {
            EoltDto eoltDto = new EoltDto();
            eoltDto.setLocation(eoltEntity1.getLocation());
            eoltDto.setEoltName(eoltEntity1.getEoltName());
            eoltDtos.add(eoltDto);
        }
        return eoltDtos;
    }
}
