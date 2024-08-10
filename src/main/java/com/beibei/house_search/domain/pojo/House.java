package com.beibei.house_search.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@ApiModel(value = "房子实体",description = "房子实体")
public class House {
//    @ApiModelProperty(value = "主键",required = true)
    @TableId
    private Long houseId;
//    @ApiModelProperty(value = "商品房名称",required = true)
    private String houseName;
//    @ApiModelProperty(value = "地址id",required = true)
    private Long locationId;
//    @ApiModelProperty(value = "房间数",required = true)
    private Integer roomNumber;
//    @ApiModelProperty(value = "大厅数",required = true)
    private Integer hallNumber;
//    @ApiModelProperty(value = "面积（平方米）",required = true)
    private  Float area;
//    @ApiModelProperty(value = "价格（元/每平方）",required = true)
    private Integer price;
//    @ApiModelProperty(value = "总价",required = true)
    private Integer totalPrice;
//    @ApiModelProperty(value = "上传时间",required = true)
    private LocalDateTime uploadTime;
//    @ApiModelProperty(value = "管理公司",required = true)
    private String company;
//    @ApiModelProperty(value = "联系电话",required = true)
    private String contactPhone;
}
