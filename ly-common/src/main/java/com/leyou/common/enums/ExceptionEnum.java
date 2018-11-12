package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-12:58
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum
{
	PRICE_CANNOT_BE_NULL(400, "价格不能为空！"),
	BRAND_NOT_FIND(404, "品牌没有查到！"),
	CATEGORY_NOT_FIND(404, "商品分类没有查到"),
	BRAND_SAVE_ERROR(500, "品牌新增失败"),
	WRONG_FILE_TYPE(400, "错误的文件类型"),
	UPLOAD_FILE_FAILED(500, "文件上传失败"),
	IMAGE_NOT_FIND(404, "图片不存在"),
	SPEC_GROUP_NOT_FIND(404, "商品规格组未查到"),
	SPEC_GROUP_SAVE_FAILED(500, "商品规格组添加失败"),
	SPEC_GROUP_UPDATE_FAILED(500, "商品规格组修改失败"),
	SPEC_GROUP_REMOVE_FAILED(500, "商品规格组删除失败"),
	SPEC_PARAM_NOT_FIND(404, "商品规格参数未查到"),
	SPEC_PARAM_SAVE_FAILED(500, "商品规格参数添加失败"),
	SPEC_PARAM_REMOVE_FAILED(500, "商品规格参数删除失败"),
	SPEC_PARAM_UPDATE_FAILED(500, "商品规格参数修改失败"),
	GOODS_NOT_FIND(404, "商品未查到"),
	GOODS_SAVE_FAILED(500, "商品新增失败"),
	GOODS_DETAIL_NOT_FIND(404, "商品详情未查到"),
	GOODS_SKU_NOT_FIND(404, "商品SKU未查到"),
	GOODS_STOCK_NOT_FIND(404, "商品详情未查到"),
	GOODS_SPU_PUDATE_FAILED(500, "商品SPU修改失败"),
	GOODS_DETAIL_PUDATE_FAILED(500, "商品详情修改失败"),
	GOODS_SKU_DELETE_FAILED(500, "商品SKU删除失败"),
	GOODS_STOCK_ADD_FAILED(500, "商品库存添加失败"),
	GOODS_SKU_SAVE_FAILED(500, "商品SKU添加失败")
	;
	private int code;
	private String msg;
}
