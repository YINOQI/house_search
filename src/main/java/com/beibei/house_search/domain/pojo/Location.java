package com.beibei.house_search.domain.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@ApiModel(value = "地址信息实体",description = "地址信息实体")
public class Location {
//    @ApiModelProperty(value = "主键",required = true)
    private Long locationId;
//    @ApiModelProperty(value = "房屋类型（1：商品房，2：租房）",required = true)
    private Integer type;
//    @ApiModelProperty(value = "街道地址",required = true)
    private String street;
//    @ApiModelProperty(value = "区",required = true)
    private String district;
//    @ApiModelProperty(value = "城市/县",required = true)
    private String city;
//    @ApiModelProperty(value = "省",required = true)
    private String state;
//    @ApiModelProperty(value = "邮政编码",required = true)
    private String postal_code;
//    @ApiModelProperty(value = "国家",required = true)
    private String country;
}
