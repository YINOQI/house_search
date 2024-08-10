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
//@ApiModel(value = "租房实体",description = "租房实体")
public class RentHouse {
    //    @ApiModelProperty(value = "主键",required = true)
    @TableId
    private Long rentHouseId;
    //    @ApiModelProperty(value = "地址id",required = true)
    private Long locationId;
    //    @ApiModelProperty(value = "房间数",required = true)
    private Integer roomNumber;
    //    @ApiModelProperty(value = "大厅数",required = true)
    private Integer hallNumber;
    //    @ApiModelProperty(value = "面积（平方米）",required = true)
    private Float area;
    //    @ApiModelProperty(value = "租金（元/月）",required = true)
    private Integer rent;
    //    @ApiModelProperty(value = "上传时间",required = true)
    private LocalDateTime uploadTime;
}
