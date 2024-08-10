package com.beibei.house_search.service.serviceimpl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.common.result.Results;
import com.beibei.house_search.domain.dto.PageDTO;
import com.beibei.house_search.domain.pojo.Location;
import com.beibei.house_search.domain.pojo.RentHouse;
import com.beibei.house_search.domain.query.RentHousePageQuery;
import com.beibei.house_search.domain.vo.RentHouseVo;
import com.beibei.house_search.mapper.RentHouseMapper;
import com.beibei.house_search.service.LocationService;
import com.beibei.house_search.service.RentHouseService;
import com.beibei.house_search.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentHouseServiceImpl extends ServiceImpl<RentHouseMapper, RentHouse> implements RentHouseService {
    private final StringRedisTemplate stringRedisTemplate;
    private final LocationService locationService;

    @Override
    public Result<RentHouseVo> saveRentHouse(RentHouse rentHouse, Location location) {
        // 保存位置
        locationService.save(location);
        // 设置该租房的地址位置信息id
        rentHouse.setLocationId(location.getLocationId());
        // 保存租房信息
        this.save(rentHouse);
        // 转化成RentHouseVo
        RentHouseVo rentHouseVo = BeanUtils.copyBean(rentHouse, RentHouseVo.class);
        // 设置位置信息
        rentHouseVo.setLocation(location);
        // 保存到redis
        stringRedisTemplate.opsForValue().set(rentHouse.getRentHouseId().toString(), JSONUtil.toJsonStr(rentHouseVo));
        // 返回rentHouseVo信息
        return Results.success(rentHouseVo);
    }

    @Override
    @Transactional
    public Result<String> deleteRentHouse(Long rentHouseId) {
        RentHouse rentHouse = this.getById(rentHouseId);
        locationService.removeById(rentHouse.getLocationId());
        this.removeById(rentHouseId);
        return Results.success("删除成功");
    }

    @Override
    public Result<RentHouseVo> updateRentHouse(RentHouse rentHouse) {
        Location location = locationService.getById(rentHouse.getLocationId());

        return updateRentHouse(rentHouse,location);
    }

    @Override
    public Result<RentHouseVo> updateRentHouse(RentHouse rentHouse,Location location) {
        this.updateById(rentHouse);
        locationService.updateById(location);
        // 转化成RentHouseVo
        RentHouseVo rentHouseVo = BeanUtils.copyBean(rentHouse, RentHouseVo.class);
        // 设置位置信息
        rentHouseVo.setLocation(location);
        // 保存到redis
        stringRedisTemplate.opsForValue().set(rentHouse.getRentHouseId().toString(), JSONUtil.toJsonStr(rentHouseVo));
        // 返回rentHouseVo信息
        return Results.success(rentHouseVo);
    }

    @Override
    public Result<RentHouseVo> getRentHouseById(Long rentHouseId) {
        String rentHouseVoStr = stringRedisTemplate.opsForValue().get(rentHouseId.toString());
        if (StrUtil.isNotBlank(rentHouseVoStr)) {
            RentHouseVo rentHouseVo = JSONUtil.toBean(rentHouseVoStr, RentHouseVo.class);
            return Results.success(rentHouseVo);
        } else {
            RentHouse rentHouse = this.getById(rentHouseId);
            Location location = locationService.getById(rentHouse.getLocationId());

            RentHouseVo rentHouseVo = BeanUtils.copyBean(rentHouse, RentHouseVo.class);
            rentHouseVo.setLocation(location);
            return Results.success(rentHouseVo);
        }
    }

    @Override
    public Result<PageDTO<RentHouseVo>> getRentHousePage(RentHousePageQuery rentHousePageQuery) {
        Page<RentHouse> page = this.lambdaQuery()
                .page(rentHousePageQuery.toMpPageDefaultSortByCreateTimeDesc());

        List<RentHouse> pageRecords = page.getRecords();
        List<Long> rentHouseIds = pageRecords.stream().map(RentHouse::getRentHouseId).collect(Collectors.toList());
        Map<Long, Location> locationMap = locationService.listByIds(rentHouseIds).stream().collect(Collectors.toMap(Location::getLocationId, o -> o));

        List<RentHouseVo> houseVoList = new ArrayList<>();
        pageRecords.forEach( house ->{
            Location location = locationMap.get(house.getLocationId());
            RentHouseVo rentHouseVo = BeanUtils.copyBean(house, RentHouseVo.class).setLocation(location);
            houseVoList.add(rentHouseVo);
        });

        PageDTO<RentHouseVo> houseVoPageDTO = PageDTO.of(page, houseVoList);

        return Results.success(houseVoPageDTO);
    }
}
