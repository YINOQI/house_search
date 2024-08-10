package com.beibei.house_search.domain.vo;

import com.beibei.house_search.domain.pojo.Location;
import com.beibei.house_search.domain.pojo.RentHouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class RentHouseVo extends RentHouse {
    private Location location;
}
