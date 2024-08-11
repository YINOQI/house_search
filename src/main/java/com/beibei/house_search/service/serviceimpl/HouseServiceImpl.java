package com.beibei.house_search.service.serviceimpl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.common.result.Results;
import com.beibei.house_search.domain.dto.HouseDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {
    private final StringRedisTemplate stringRedisTemplate;
    private final LocationService locationService;

    @Override
    public Result<HouseDto> saveHouse(HouseDto houseDto) {
        Location location = houseDto.getLocation();
        locationService.save(location);

        houseDto.setLocation(location)
                .setLocationId(location.getLocationId());
        this.save(houseDto);

        stringRedisTemplate.opsForValue().set(
                houseDto.getHouseId().toString(),
                JSONUtil.toJsonStr(houseDto)
        );

        return Results.success(houseDto);
    }

    @Override
    public Result<String> deleteHouse(Long houseId) {
        this.removeById(houseId);
        stringRedisTemplate.delete(houseId.toString());
        return Results.success("删除成功");
    }

    @Override
    public Result<HouseDto> updateHouse(HouseDto houseDto) {
        this.updateById(houseDto);
        Location location = houseDto.getLocation();
        locationService.updateById(location);
        stringRedisTemplate.opsForValue().set(
                houseDto.getHouseId().toString(),
                JSONUtil.toJsonStr(houseDto.setLocation(location).setLocationId(location.getLocationId()))
        );
        return Results.success(houseDto);
    }

    @Override
    public Result<HouseVo> getHouseById(Long houseId) {
        String houseStr = stringRedisTemplate.opsForValue().get(houseId.toString());
        House house;
        if (StrUtil.isNotBlank(houseStr)) {
            house = JSONUtil.toBean(houseStr, House.class);
        } else {
            house = this.getById(houseId);
        }

        Location location = locationService.getById(house.getLocationId());

        HouseVo houseVo = BeanUtils.copyBean(house, HouseVo.class);
        houseVo.setLocation(location);
        return Results.success(houseVo);
    }

    @Override
    public Result<PageDTO<HouseVo>> getHousePage(HousePageQuery housePageQuery) {
        Page<House> page = this.lambdaQuery()
                .eq(StrUtil.isNotEmpty(housePageQuery.getKey()), House::getHouseName, housePageQuery.getKey())
                .page(housePageQuery.toMpPageDefaultSortByCreateTimeDesc());

        List<House> pageRecords = page.getRecords();
        List<Long> houseIds = pageRecords.stream().map(House::getHouseId).collect(Collectors.toList());
        Map<Long, Location> locationMap = locationService.listByIds(houseIds).stream().collect(Collectors.toMap(Location::getLocationId, o -> o));

        List<HouseVo> houseVoList = new ArrayList<>();
        pageRecords.forEach( house ->{
            Location location = locationMap.get(house.getLocationId());
            HouseVo houseVo = BeanUtils.copyBean(house, HouseVo.class).setLocation(location);
            houseVoList.add(houseVo);
        });

        PageDTO<HouseVo> houseVoPageDTO = PageDTO.of(page, houseVoList);

        return Results.success(houseVoPageDTO);
    }


}
