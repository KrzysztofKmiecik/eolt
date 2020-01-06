package com.java26.eolt.service;

import com.java26.eolt.Utils.MySecurityUtil;
import com.java26.eolt.dto.VariantDto;
import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.entity.VariantEntity;
import com.java26.eolt.myEnum.Variant;
import com.java26.eolt.myEnum.VariantStatus;
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
public class VariantService {

    final VariantRepository variantRepository;
    final EoltRepository eoltRepository;
    final VariantHistoryService variantHistoryService;
    final MySecurityUtil mySecurityUtil;

    public List<VariantDto> findAllVariants(String eoltName) {
        log.info("VariantService:findAllVariants");

        List<VariantEntity> variantEntities = variantRepository.findByEolt(
                eoltRepository.findByEoltName(eoltName)
                        .orElseThrow(() -> new EntityNotFoundException(eoltName)));
        List<VariantDto> variantDtos = new ArrayList<>();
        for (VariantEntity variantEntity : variantEntities) {
            VariantDto variantDto = getVariantDto(variantEntity);
            variantDtos.add(variantDto);
        }
        return variantDtos;
    }

    public void setVariantStatus(VariantDto variantDto, VariantStatus variantStatus) {
        variantDto.setVariantStatus(variantStatus);
    }

    public VariantDto findVariant(String dpn, String eoltName) {
        VariantDto variantDto = new VariantDto();
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName).orElseThrow(() -> new EntityNotFoundException(eoltName));
        VariantEntity variantEntity = variantRepository.findByDpnAndEolt(dpn, eoltEntity).orElseThrow(() -> new EntityNotFoundException(dpn));
        myMap(variantEntity, variantDto);
        return variantDto;
    }

    private VariantDto getVariantDto(VariantEntity variantEntity) {
        VariantDto variantDto = new VariantDto();
        myMap(variantEntity, variantDto);
        return variantDto;
    }

    /*  public VariantDto findByDpn(String dpn) {

          VariantEntity variantEntity = variantRepository.findByDpn(dpn)
                  .orElseThrow(() -> new EntityNotFoundException(dpn));
          VariantDto variantDto = new VariantDto();
          variantDto.setDpn(variantEntity.getDpn());
          return variantDto;
      }*/

    public void create(VariantDto variantDto, String eoltName) {
        log.info("VariantService:create");
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        if (!variantRepository.findByDpnAndEolt(variantDto.getDpn(), eoltEntity)
                .isPresent()) {
            VariantEntity variantEntity = getVariantEntity(variantDto, eoltEntity);
            variantEntity.setEolt(eoltEntity);

            variantRepository.save(variantEntity);
        } else {
            throw new IllegalArgumentException("variant " + variantDto.getDpn() + " has already exist");
        }
    }

    private VariantEntity getVariantEntity(VariantDto variantDto, EoltEntity eoltEntity) {
        VariantEntity variantEntity = new VariantEntity();
        myMap(variantDto, variantEntity);
        return variantEntity;
    }

   /* public void delete(Long id) {
        log.info("VariantService:delete");

        VariantEntity variantEntity = variantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));

        variantRepository.deleteById(id);

    }*/

    public void delete(String dpn, String eoltName) {
        log.info("VariantService:delete dpn_eoltName");

        VariantEntity variantEntity = variantRepository.findByDpnAndEolt(dpn, eoltRepository.findByEoltName(eoltName).orElseThrow(() -> new EntityNotFoundException(eoltName)))
                .orElseThrow(() -> new EntityNotFoundException(dpn));

        variantRepository.deleteById(variantEntity.getId());

    }

    public void deleteAllVariantsFromEoltName(String eoltName) {
        log.info("VariantService:deleteALLdpn_eoltName");
        List<VariantEntity> variantEntities = variantRepository.findByEolt(
                eoltRepository.findByEoltName(eoltName)
                        .orElseThrow(() -> new EntityNotFoundException(eoltName)));

        variantRepository.deleteAll(variantEntities);

    }

    public void update(VariantDto variantDto, String eoltName) {
        log.info("VariantService:updateVariant");


        boolean hasTesterRole = mySecurityUtil.checkRole("ROLE_TESTER");
        boolean hasQualityRole = mySecurityUtil.checkRole("ROLE_QUALITY");

        if (hasTesterRole) {
            variantDto.setTestEng(mySecurityUtil.getAuthenticationUserName());
        }

        if (hasQualityRole) {
            variantDto.setQualityEng(mySecurityUtil.getAuthenticationUserName());
        }

        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        VariantEntity variantEntity = variantRepository.findByDpnAndEolt(variantDto.getDpn(), eoltEntity)
                .orElseThrow(() -> new EntityNotFoundException(variantDto.getDpn()));
        myMap(variantDto, variantEntity);
    }

    private <T extends Variant, I extends Variant> void myMap(T input, I output) {
        output.setDpn(input.getDpn());
        output.setCustomer(input.getCustomer());
        output.setMachineCycleTime(input.getMachineCycleTime());
        output.setFixture(input.getFixture());
        output.setTestEng(input.getTestEng());
        output.setQualityEng(input.getQualityEng());
        output.setVariantStatus(input.getVariantStatus());
    }


}
