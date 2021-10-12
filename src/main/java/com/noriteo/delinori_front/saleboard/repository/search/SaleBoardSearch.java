package com.noriteo.delinori_front.saleboard.repository.search;

import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleBoardSearch {

    Page<SaleBoard> search(char[] typeArr, String keyword, Pageable pageable);

}
