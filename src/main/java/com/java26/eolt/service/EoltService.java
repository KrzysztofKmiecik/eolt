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

    /*
    * private String eoltName;
        private String location;
        private String assetNumber;
        private String AR;
        private String netName;
        private String macAdress;
        private Integer productionYear;
        private SupplierName supplierName;
        private SystemVersion systemVersion;
        private String documentationLink;
    *
    * */
    public List<EoltDto> findAll() {
        log.info("EoltService:findAll");
        List<EoltEntity> eoltEntities = eoltRepository.findAll();
        List<EoltDto> eoltDtos = new ArrayList<>();
        for (EoltEntity eoltEntity : eoltEntities) {
            EoltDto eoltDto = mapEoltEntityToEoltDto(eoltEntity);
            eoltDtos.add(eoltDto);
        }
        return eoltDtos;
    }

    public EoltDto findByName(String eoltName) {
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        EoltDto eoltDto = mapEoltEntityToEoltDto(eoltEntity);
        return eoltDto;
    }

    public void create(EoltDto eoltDto) {
        log.info("EoltService:create");
        EoltEntity eoltEntity = mapEoltDtoToEoltEntity(eoltDto);
        eoltRepository.save(eoltEntity);
    }

    //@Transactional
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
            EoltDto eoltDto = mapEoltEntityToEoltDto(eoltEntity1);
            eoltDtos.add(eoltDto);
        }
        return eoltDtos;
    }

    public List<EoltDto> findEoltContaining(String searchString) {
        List<EoltDto> eoltDtos = new ArrayList<>();
        List<EoltEntity> eoltEntities = eoltRepository.findMyEoltInEoltNameORLocationWithSearchString(searchString);
        for (EoltEntity eoltEntity : eoltEntities) {
            EoltDto eoltDto =mapEoltEntityToEoltDto(eoltEntity);
            eoltDtos.add(eoltDto);
        }

        return eoltDtos;
    }

    private EoltDto mapEoltEntityToEoltDto(EoltEntity eoltEntity) {
        EoltDto eoltDto = new EoltDto();
        eoltDto.setEoltName(eoltEntity.getEoltName());
        eoltDto.setLocation(eoltEntity.getLocation());
        eoltDto.setAssetNumber(eoltEntity.getAssetNumber());
        eoltDto.setAR(eoltEntity.getAR());
        eoltDto.setNetName(eoltEntity.getNetName());
        eoltDto.setMacAdress(eoltEntity.getMacAdress());
        eoltDto.setProductionYear(eoltEntity.getProductionYear());
        eoltDto.setSupplierName(eoltEntity.getSupplierName());
        eoltDto.setSystemVersion(eoltEntity.getSystemVersion());
        eoltDto.setDocumentationLink(eoltEntity.getDocumentationLink());
        return eoltDto;
    }

    private EoltEntity mapEoltDtoToEoltEntity(EoltDto eoltDto) {
        EoltEntity eoltEntity = new EoltEntity();
        eoltEntity.setEoltName(eoltDto.getEoltName());
        eoltEntity.setLocation(eoltDto.getLocation());
        eoltEntity.setAssetNumber(eoltDto.getAssetNumber());
        eoltEntity.setAR(eoltDto.getAR());
        eoltEntity.setNetName(eoltDto.getNetName());
        eoltEntity.setMacAdress(eoltDto.getMacAdress());
        eoltEntity.setProductionYear(eoltDto.getProductionYear());
        eoltEntity.setSupplierName(eoltDto.getSupplierName());
        eoltEntity.setSystemVersion(eoltDto.getSystemVersion());
        eoltEntity.setDocumentationLink(eoltDto.getDocumentationLink());
        return eoltEntity;
    }
}
