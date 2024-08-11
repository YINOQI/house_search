package com.beibei.house_search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.domain.dto.PageDTO;
import com.beibei.house_search.domain.dto.RentHouseDto;
import com.beibei.house_search.domain.pojo.RentHouse;
import com.beibei.house_search.domain.query.RentHousePageQuery;
import com.beibei.house_search.domain.vo.RentHouseVo;

public interface RentHouseService extends IService<RentHouse> {
    /**
     * 保存租房房源
     * @param rentHouseDto
     * @return
     */
    Result<RentHouseVo> saveRentHouse(RentHouseDto rentHouseDto);

    /**
     * 删除租房房源
     * @param rentHouseId
     * @return
     */
    Result<String> deleteRentHouse(Long rentHouseId);

    /**
     * 更新租房房源信息
     * @param rentHouseDto
     * @return
     */
    Result<RentHouseVo> updateRentHouse(RentHouseDto rentHouseDto);

    /**
     * 根据id获取租房房源信息
     * @param rentHouseId
     * @return
     */
    Result<RentHouseVo> getRentHouseById(Long rentHouseId);

    /**
     * 分页获取租房房源
     * @param rentHousePageQuery
     * @return
     */
    Result<PageDTO<RentHouseVo>> getRentHousePage(RentHousePageQuery rentHousePageQuery);
}
