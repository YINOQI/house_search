package com.beibei.house_search.controller;

import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.domain.dto.PageDTO;
import com.beibei.house_search.domain.dto.RentHouseDto;
import com.beibei.house_search.domain.query.RentHousePageQuery;
import com.beibei.house_search.domain.vo.RentHouseVo;
import com.beibei.house_search.service.RentHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent-house")
@RequiredArgsConstructor
public class RentHouseController {
    private final RentHouseService rentHouseService;

    @GetMapping
    public Result<RentHouseVo> getRentHouseById(Long rentHouseId){
        return rentHouseService.getRentHouseById(rentHouseId);
    }

    @PostMapping("/save-rent-house")
    public Result<RentHouseVo> saveRentHouse(@RequestBody RentHouseDto rentHouseDto){
        return rentHouseService.saveRentHouse(rentHouseDto);
    }
    @PostMapping("/update-rent-house")
    public Result<RentHouseVo> updateRentHouse(@RequestBody RentHouseDto rentHouseDto){
            return rentHouseService.updateRentHouse(rentHouseDto);
    }
    @DeleteMapping("/delete-rent-house")
    public Result<String> deleteRentHouse(Long rentHouseId){
        return rentHouseService.deleteRentHouse(rentHouseId);
    }

    @GetMapping("/rent-house-page")
    Result<PageDTO<RentHouseVo>> getRentHousePage(RentHousePageQuery rentHousePageQuery){
        return rentHouseService.getRentHousePage(rentHousePageQuery);
    }
}
