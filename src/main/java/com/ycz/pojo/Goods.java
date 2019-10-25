package com.ycz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer id;
    private String name;
    private Date times;
    private String picture;
    private BigDecimal price;
    private String intro;
    private Integer typeid;
    private String banner;
}
