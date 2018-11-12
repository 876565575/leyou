package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-13:39
 */
@Data
public class ExceptionResult
{
	private int stats;
	private String message;
	private long timestamp;

	public ExceptionResult(ExceptionEnum exceptionEnum)
	{
		this.message = exceptionEnum.getMsg();
		this.stats = exceptionEnum.getCode();
		this.timestamp = System.currentTimeMillis();
	}
}
