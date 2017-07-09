package com.edu.nju.se.integration.service;
import com.edu.nju.se.integration.vo.PlaneVO;
import java.sql.Date;
import java.util.List;
/**
 * Created by darxan on 2017/7/8.
 */
public interface PredictService {

    /**
     * 预测应当买票的日期
     * PlaneVO中的价格无效
     * @return 返回的一个天数， 指的是在出发日期前预先多久买票，这个天数存储在{@link PlaneVO#shouldMoveUpDays}
     *
     */
    List<PlaneVO> predictBuyDate(Date departDate, String depart, String destination);

    /**
     * 预测应当买票的日期,
     * PlaneVO中的价格无效
     * @return 返回的一个天数， 指的是在出发日期前预先多久买票， 这个天数存储在{@link PlaneVO#shouldMoveUpDays}
     */
    List<PlaneVO> predictBuyDate(Date departDate, String flightNum);

}
