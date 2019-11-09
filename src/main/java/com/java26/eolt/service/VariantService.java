package com.java26.eolt.service;

import com.java26.eolt.dto.VariantDto;
import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.entity.VariantEntity;
import com.java26.eolt.repository.EoltRepository;
import com.java26.eolt.repository.VariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VariantService {

    final VariantRepository variantRepository;
    final EoltRepository eoltRepository;

    public List<VariantDto> findAllVariants(String eoltName) {
        log.info("VariantService:findAllVariants");

        List<VariantEntity> variantEntities = variantRepository.findByEolt(
                eoltRepository.findByEoltName(eoltName)
                        .orElseThrow(() -> new EntityNotFoundException(eoltName)));
        List<VariantDto> variantDtos = new ArrayList<>();
        for (VariantEntity variantEnt : variantEntities) {
            VariantDto variantDto = new VariantDto();
            variantDto.setDpn(variantEnt.getDpn());
            variantDtos.add(variantDto);
        }
        return variantDtos;
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


        VariantEntity variantEntity = new VariantEntity();

        if (!variantRepository.findByDpnAndEolt(variantDto.getDpn(), eoltEntity)
                .isPresent()) {
            variantEntity.setDpn(variantDto.getDpn());
            variantEntity.setEolt(eoltEntity);
            variantRepository.save(variantEntity);

        } else {
            throw new IllegalArgumentException("variant " + variantDto.getDpn() + " has already exist");
        }


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

}
