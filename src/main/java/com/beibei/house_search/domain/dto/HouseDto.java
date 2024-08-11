package com.beibei.house_search.domain.dto;

import com.beibei.house_search.domain.pojo.House;
import com.beibei.house_search.domain.pojo.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class HouseDto extends House {
    private Location location;
}
