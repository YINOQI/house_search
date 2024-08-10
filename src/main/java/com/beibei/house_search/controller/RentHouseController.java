package com.beibei.house_search.controller;

import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.domain.dto.PageDTO;
import com.beibei.house_search.domain.pojo.Location;
import com.beibei.house_search.domain.pojo.RentHouse;
import com.beibei.house_search.domain.query.RentHousePageQuery;
import com.beibei.house_search.domain.vo.RentHouseVo;
import com.beibei.house_search.service.RentHouseService;
import com.beibei.house_search.utils.BeanUtils;
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
    public Result<RentHouseVo> saveRentHouse(@RequestBody RentHouse rentHouse, @RequestBody Location location){
        return rentHouseService.saveRentHouse(rentHouse,location);
    }
    @PostMapping("/update-rent-house")
    public Result<RentHouseVo> updateRentHouse(@RequestBody RentHouse rentHouse, @RequestBody Location location){
        if (BeanUtils.isEmpty(location)) {
            return rentHouseService.updateRentHouse(rentHouse);
        }else{
            return rentHouseService.updateRentHouse(rentHouse,location);
        }
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
