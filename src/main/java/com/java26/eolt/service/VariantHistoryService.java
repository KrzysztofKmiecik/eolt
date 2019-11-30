package com.java26.eolt.service;

import com.java26.eolt.dto.VariantDto;
import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.entity.VariantHistoryEntity;
import com.java26.eolt.myEnum.ModificationReason;
import com.java26.eolt.myEnum.Variant;
import com.java26.eolt.myEnum.VariantStatus;
import com.java26.eolt.repository.EoltRepository;
import com.java26.eolt.repository.VariantHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
public class VariantHistoryService {

    final VariantHistoryRepository variantHistoryRepository;
    final EoltRepository eoltRepository;


    public List<VariantDto> findAllVariants(String eoltName) {
        log.info("VariantHistoryService:findAllVariants");

        List<VariantHistoryEntity> variantHistoryEntities = variantHistoryRepository.findByEolt(
                eoltRepository.findByEoltName(eoltName)
                        .orElseThrow(() -> new EntityNotFoundException(eoltName)));
        List<VariantDto> variantDtos = new ArrayList<>();
        for (VariantHistoryEntity variantHistoryEntity : variantHistoryEntities) {
            VariantDto variantDto = getVariantDto(variantHistoryEntity);
            variantDtos.add(variantDto);
        }
        return variantDtos;
    }

    public void setVariantStatus(VariantDto variantDto, VariantStatus variantStatus) {
        variantDto.setVariantStatus(variantStatus);
    }

    private VariantDto getVariantDto(VariantHistoryEntity variantHistoryEntity) {
        VariantDto variantDto = new VariantDto();
        myHistoryMap(variantHistoryEntity, variantDto);
        return variantDto;
    }

    /*  public VariantDto findByDpn(String dpn) {

          VariantEntity variantEntity = variantRepository.findByDpn(dpn)
                  .orElseThrow(() -> new EntityNotFoundException(dpn));
          VariantDto variantDto = new VariantDto();
          variantDto.setDpn(variantEntity.getDpn());
          return variantDto;
      }*/

    public void create(VariantDto variantDto, String eoltName, ModificationReason modificationReason) {
        log.info("VariantService:create");
        LocalDateTime localDateTime = LocalDateTime.now();
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        VariantHistoryEntity variantHistoryEntity = getVariantEntity(variantDto, eoltEntity);
        variantHistoryEntity.setEolt(eoltEntity);
        variantHistoryEntity.setModificationReason(modificationReason);
        variantHistoryEntity.setModificationDateTime(localDateTime);
        variantHistoryRepository.save(variantHistoryEntity);

    }

    private VariantHistoryEntity getVariantEntity(VariantDto variantDto, EoltEntity eoltEntity) {
        VariantHistoryEntity variantHistoryEntity = new VariantHistoryEntity();
        myHistoryMap(variantDto, variantHistoryEntity);
        return variantHistoryEntity;
    }

   /* public void delete(Long id) {
        log.info("VariantService:delete");

        VariantEntity variantEntity = variantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));

        variantRepository.deleteById(id);

    }*/

   /* public void delete(String dpn, String eoltName) {
        log.info("VariantService:delete dpn_eoltName");

        VariantHistoryEntity variantHistoryEntity = variantHistoryRepository.findByDpnAndEolt(dpn, eoltRepository.findByEoltName(eoltName).orElseThrow(() -> new EntityNotFoundException(eoltName)))
                .orElseThrow(() -> new EntityNotFoundException(dpn));

        variantHistoryRepository.deleteById(variantHistoryEntity.getId());

    }*/

    public void deleteAllVariantsFromEoltName(String eoltName) {
        log.info("VariantHistoryService:deleteALLdpn_eoltName");
        List<VariantHistoryEntity> variantHistoryEntities = variantHistoryRepository.findByEolt(
                eoltRepository.findByEoltName(eoltName)
                        .orElseThrow(() -> new EntityNotFoundException(eoltName)));

        variantHistoryRepository.deleteAll(variantHistoryEntities);

    }

   /* public void update(VariantDto variantDto, String eoltName) {
        log.info("VariantService:updateVariant");
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        VariantHistoryEntity variantHistoryEntity = variantHistoryRepository.findByDpnAndEolt(variantDto.getDpn(), eoltEntity)
                .orElseThrow(() -> new EntityNotFoundException(variantDto.getDpn()));
        myMap(variantDto, variantHistoryEntity);
    }*/

    private <T extends Variant, I extends Variant> void myHistoryMap(T input, I output) {
        output.setDpn(input.getDpn());
        output.setCustomer(input.getCustomer());
        output.setMachineCycleTime(input.getMachineCycleTime());
        output.setFixture(input.getFixture());
        output.setTestEng(input.getTestEng());
        output.setQualityEng(input.getQualityEng());
        output.setVariantStatus(input.getVariantStatus());
        output.setModificationDateTime(input.getModificationDateTime());
        output.setModificationReason(input.getModificationReason());
    }

    public List<VariantDto> findAllModifications(String eoltName, String variantName2) {
        log.info("VariantHistoryService:findAllModifications");

        List<VariantHistoryEntity> variantHistoryEntities = variantHistoryRepository.findByDpnAndEolt(
                variantName2, eoltRepository.findByEoltName(eoltName)
                        .orElseThrow(() -> new EntityNotFoundException(eoltName)));

        List<VariantDto> variantDtos = new ArrayList<>();
        for (VariantHistoryEntity variantHistoryEntity : variantHistoryEntities) {
            VariantDto variantDto = getVariantDto(variantHistoryEntity);
            variantDtos.add(variantDto);
        }
        return variantDtos;

    }
}
