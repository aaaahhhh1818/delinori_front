package com.noriteo.delinori_front.saleboard.service;

import com.noriteo.delinori_front.saleboard.dto.PageRequestDTO;
import com.noriteo.delinori_front.saleboard.dto.PageResponseDTO;
import com.noriteo.delinori_front.saleboard.dto.SaleBoardDTO;
import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import com.noriteo.delinori_front.saleboard.repository.SaleBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SaleBoardServiceImpl implements SaleBoardService {

    private final ModelMapper modelMapper;
    private final SaleBoardRepository saleBoardRepository;

    @Override
    public Long register(SaleBoardDTO saleBoardDTO) {

        SaleBoard saleBoard = modelMapper.map(saleBoardDTO, SaleBoard.class);

        saleBoardRepository.save(saleBoard);

        return saleBoard.getSno();
    }

    @Override
    public PageResponseDTO<SaleBoardDTO> getList(PageRequestDTO pageRequestDTO) {

        char[] typeArr = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("sno").descending());

        Page<SaleBoard> result = saleBoardRepository.search(typeArr, keyword, pageable);

        List<SaleBoardDTO> dtoList = result.get().map(
                saleBoard -> modelMapper.map(saleBoard, SaleBoardDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return new PageResponseDTO<>(pageRequestDTO, (int)totalCount, dtoList);

    }

    @Override
    public SaleBoardDTO read(Long sno) {

        Optional<SaleBoard> result = saleBoardRepository.findById(sno);

        if(result.isEmpty()) {
            throw new RuntimeException("NOT FOUND");
        }

        return modelMapper.map(result.get(), SaleBoardDTO.class);
    }

    @Override
    public void modify(SaleBoardDTO saleBoardDTO) {

        Optional<SaleBoard> result = saleBoardRepository.findById(saleBoardDTO.getSno());

        if(result.isEmpty()) {
            throw new RuntimeException("NOT FOUND");
        }

        SaleBoard saleBoard = result.get();
        saleBoard.change(saleBoardDTO.getTitle(), saleBoardDTO.getContent());
        saleBoardRepository.save(saleBoard);

    }

    @Override
    public void remove(Long sno) {

        saleBoardRepository.deleteById(sno);

    }


}
