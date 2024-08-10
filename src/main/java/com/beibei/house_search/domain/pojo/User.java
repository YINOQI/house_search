package com.beibei.house_search.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
//@ApiModel(value = "用户实体",description = "用户实体")
public class User {
    //   @ApiModelProperty(value = "主键",required = true)
    @TableId
    private Long userId;
    //   @ApiModelProperty(value = "名字",required = true)
    private String username;
    //   @ApiModelProperty(value = "手机号",required = true)
    private String phone;
    //   @ApiModelProperty(value = "身份证",required = true)
    private String idCard;
}
