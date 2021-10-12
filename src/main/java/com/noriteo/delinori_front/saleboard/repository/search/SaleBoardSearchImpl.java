package com.noriteo.delinori_front.saleboard.repository.search;

import com.noriteo.delinori_front.saleboard.entity.QSaleBoard;
import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SaleBoardSearchImpl extends QuerydslRepositorySupport implements SaleBoardSearch {

    public SaleBoardSearchImpl() {
        super(SaleBoard.class);
    }

    @Override
    public Page<SaleBoard> search(char[] typeArr, String keyword, Pageable pageable) {

        log.info("------------search");

        QSaleBoard saleBoard = QSaleBoard.saleBoard;
        JPQLQuery<SaleBoard> jpqlQuery = from(saleBoard);

        if(typeArr != null && typeArr.length > 0){

            BooleanBuilder condition = new BooleanBuilder();

            for(char type: typeArr){
                if(type == 'T'){
                    condition.or(saleBoard.title.contains(keyword));
                }else if(type =='C'){
                    condition.or(saleBoard.content.contains(keyword));
                }else if(type == 'W'){
                    condition.or(saleBoard.writer.contains(keyword));
                }
            }
            jpqlQuery.where(condition);
        }

        jpqlQuery.where(saleBoard.sno.gt(0L)); //bno > 0

        JPQLQuery<SaleBoard> pagingQuery =
                this.getQuerydsl().applyPagination(pageable, jpqlQuery);

        List<SaleBoard> boardList = pagingQuery.fetch();
        long totalCount = pagingQuery.fetchCount();

        return new PageImpl<>(boardList, pageable, totalCount);

    }

}
