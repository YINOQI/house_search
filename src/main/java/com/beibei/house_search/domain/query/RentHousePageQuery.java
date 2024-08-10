package com.beibei.house_search.domain.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RentHousePageQuery extends PageQuery {
    private String key;
}
