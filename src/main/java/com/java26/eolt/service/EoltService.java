package com.java26.eolt.service;

import com.java26.eolt.dto.EoltDto;
import com.java26.eolt.dto.VariantDto;
import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.entity.VariantEntity;
import com.java26.eolt.myEnum.Eolt;
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
        for (EoltEntity eoltEntity : eoltEntities) {
            EoltDto eoltDto = new EoltDto();
            myMap(eoltEntity, eoltDto);
            eoltDtos.add(eoltDto);
        }
        return eoltDtos;
    }

    public EoltDto findByName(String eoltName) {
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        EoltDto eoltDto = new EoltDto();
        myMap(eoltEntity, eoltDto);
        return eoltDto;
    }

    public void create(EoltDto eoltDto) {
        log.info("EoltService:create");
        EoltEntity eoltEntity = new EoltEntity();
        //    mapEoltDtoToEoltEntity(eoltDto, eoltEntity);
        myMap(eoltDto, eoltEntity);
        eoltRepository.save(eoltEntity);
    }

    public void update(EoltDto eoltDto) {
        log.info("EoltService:update");
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltDto.getEoltName())
                .orElseThrow(() -> new EntityNotFoundException(eoltDto.getEoltName()));
        myMap(eoltDto, eoltEntity);
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
            myMap(eoltEntity1, eoltDto);
            eoltDtos.add(eoltDto);
        }
        return eoltDtos;
    }

    public List<EoltDto> findEoltContaining(String searchString) {
        List<EoltDto> eoltDtos = new ArrayList<>();
        List<EoltEntity> eoltEntities = eoltRepository.findMyEoltInEoltNameORLocationWithSearchString(searchString);
        for (EoltEntity eoltEntity : eoltEntities) {
            EoltDto eoltDto = new EoltDto();
            myMap(eoltEntity, eoltDto);
            eoltDtos.add(eoltDto);
        }

        return eoltDtos;
    }

    private <T extends Eolt, I extends Eolt> void myMap(T input, I output) {

        output.setEoltName(input.getEoltName());
        output.setLocation(input.getLocation());
        output.setAssetNumber(input.getAssetNumber());
        output.setAR(input.getAR());
        output.setNetName(input.getNetName());
        output.setMacAdress(input.getMacAdress());
        output.setProductionYear(input.getProductionYear());
        output.setSupplierName(input.getSupplierName());
        output.setSystemVersion(input.getSystemVersion());
        output.setDocumentationLink(input.getDocumentationLink());
    }

}
