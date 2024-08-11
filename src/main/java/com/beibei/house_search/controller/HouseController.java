package com.beibei.house_search.controller;

import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.domain.dto.HouseDto;
import com.beibei.house_search.domain.dto.PageDTO;
import com.beibei.house_search.domain.query.HousePageQuery;
import com.beibei.house_search.domain.vo.HouseVo;
import com.beibei.house_search.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;

    @GetMapping
    public Result<HouseVo> getHouseById(Long houseId){
        return houseService.getHouseById(houseId);
    }

    @PostMapping("/save-house")
    public Result<HouseDto> saveHouse(HouseDto houseDto){
        return houseService.saveHouse(houseDto);
    }
    @PostMapping("/update-house")
    public Result<HouseDto> updateHouse(HouseDto houseDto){
        return houseService.updateHouse(houseDto);
    }
    @DeleteMapping("/delete-house")
    public Result<String> deleteHouse(Long houseId){
        return houseService.deleteHouse(houseId);
    }

    @GetMapping("/house-page")
    Result<PageDTO<HouseVo>> getHousePage(HousePageQuery housePageQuery){
        return houseService.getHousePage(housePageQuery);
    }
}
