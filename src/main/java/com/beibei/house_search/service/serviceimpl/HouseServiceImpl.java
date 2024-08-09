package com.beibei.house_search.service.serviceimpl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.common.result.Results;
import com.beibei.house_search.domain.dto.PageDTO;
import com.beibei.house_search.domain.pojo.House;
import com.beibei.house_search.domain.pojo.Location;
import com.beibei.house_search.domain.query.HousePageQuery;
import com.beibei.house_search.domain.vo.HouseVo;
import com.beibei.house_search.mapper.HouseMapper;
import com.beibei.house_search.service.HouseService;
import com.beibei.house_search.service.LocationService;
import com.beibei.house_search.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {
    private final StringRedisTemplate stringRedisTemplate;
    private final LocationService locationService;
    @Override
    public Result<House> saveHouse(House house) {
        this.save(house);
        stringRedisTemplate.opsForValue().set(house.getHouseId().toString(), JSONUtil.toJsonStr(house));
        return Results.success(house);
    }

    @Override
    public Result<String> deleteHouse(Long houseId) {
        this.removeById(houseId);
        stringRedisTemplate.delete(houseId.toString());
        return Results.success("删除成功");
    }

    @Override
    public Result<House> updateHouse(House house) {
        this.updateById(house);
        stringRedisTemplate.opsForValue().set(house.getHouseId().toString(),JSONUtil.toJsonStr(house));
        return Results.success(house);
    }

    @Override
    public Result<HouseVo> getHouseById(Long houseId) {
        String houseStr = stringRedisTemplate.opsForValue().get(houseId.toString());
        House house;
        if (StrUtil.isNotBlank(houseStr)) {
            house = JSONUtil.toBean(houseStr, House.class);
        }else {
            house = this.getById(houseId);
        }

        Location location = locationService.getById(house.getLocationId());

        HouseVo houseVo = BeanUtils.copyBean(house, HouseVo.class);
        houseVo.setLocation(location);
        return Results.success(houseVo);
    }

    @Override
    public Result<PageDTO<HouseVo>> getHousePage(HousePageQuery housePageQuery) {

        return null;
    }


}
