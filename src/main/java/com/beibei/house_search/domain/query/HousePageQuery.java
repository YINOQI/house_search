package com.beibei.house_search.domain.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HousePageQuery extends PageQuery {
    private String key;
}
