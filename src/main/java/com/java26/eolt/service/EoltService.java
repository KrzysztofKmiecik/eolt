package com.java26.eolt.service;

import com.java26.eolt.dto.EoltDto;
import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.repository.EoltRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EoltService {

    final EoltRepository eoltRepository;

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

    public void delete(String eoltName) {
        log.info("EoltService:delete eoltNAme");
        EoltEntity eoltEntity = eoltRepository.findByEoltName(eoltName)
                .orElseThrow(() -> new EntityNotFoundException(eoltName));
        eoltRepository.deleteById(eoltEntity.getId());
    }
}
