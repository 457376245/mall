package com.jh.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @Author JH
 * @Date 21/1/11 16:18
 * @Description TODO
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ToString
public class CategoryVo {

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> childCategory;
}