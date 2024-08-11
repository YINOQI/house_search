package com.beibei.house_search.domain.dto;

import com.beibei.house_search.domain.pojo.Location;
import com.beibei.house_search.domain.pojo.RentHouse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RentHouseDto extends RentHouse {
    private Location location;
}
