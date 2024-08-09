package com.beibei.house_search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.domain.dto.PageDTO;
import com.beibei.house_search.domain.pojo.House;
import com.beibei.house_search.domain.query.HousePageQuery;
import com.beibei.house_search.domain.vo.HouseVo;

public interface HouseService extends IService<House> {
    /**
     * 保存房源
     * @param house
     * @return
     */
    Result<House> saveHouse(House house);

    /**
     * 删除房源
     * @param houseId
     * @return
     */
    Result<String> deleteHouse(Long houseId);

    /**
     * 更新房源信息
     * @param house
     * @return
     */
    Result<House> updateHouse(House house);

    /**
     * 根据id获取房源信息
     * @param houseId
     * @return
     */
    Result<HouseVo> getHouseById(Long houseId);

    Result<PageDTO<HouseVo>> getHousePage(HousePageQuery housePageQuery);
}
