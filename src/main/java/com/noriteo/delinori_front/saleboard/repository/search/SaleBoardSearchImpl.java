package com.noriteo.delinori_front.saleboard.repository.search;

import com.noriteo.delinori_front.saleboard.entity.QSaleBoard;
import com.noriteo.delinori_front.saleboard.entity.QSaleBoardReply;
import com.noriteo.delinori_front.saleboard.entity.SaleBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page<Object[]> searchWithReplyCount(char[] typeArr, String keyword, Pageable pageable) {

        log.info("searchWithReplyCount");

        QSaleBoard qSaleBoard = QSaleBoard.saleBoard;
        QSaleBoardReply qSaleBoardReply = QSaleBoardReply.saleBoardReply;

        JPQLQuery<SaleBoard> query = from(qSaleBoard);
        query.leftJoin(qSaleBoardReply).on(qSaleBoardReply.saleBoard.eq(qSaleBoard));
        query.groupBy(qSaleBoard);

        if(typeArr != null && typeArr.length > 0){

            BooleanBuilder condition = new BooleanBuilder();

            for(char type: typeArr){
                if(type == 'T'){
                    condition.or(qSaleBoard.title.contains(keyword));
                }else if(type =='C'){
                    condition.or(qSaleBoard.content.contains(keyword));
                }else if(type == 'W'){
                    condition.or(qSaleBoard.writer.contains(keyword));
                }
            }
            query.where(condition);
        }

        JPQLQuery<Tuple> selectQuery =
                query.select(qSaleBoard.sno, qSaleBoard.title, qSaleBoard.writer, qSaleBoard.regDate, qSaleBoardReply.count());

        this.getQuerydsl().applyPagination(pageable, selectQuery);

        log.info(selectQuery);

        List<Tuple> tupleList = selectQuery.fetch();

        long totalCount = selectQuery.fetchCount();

        List<Object[]> arr = tupleList.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList());

        return new PageImpl<>(arr, pageable, totalCount);
    }

}
