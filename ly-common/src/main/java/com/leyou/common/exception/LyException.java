package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-12:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LyException extends RuntimeException
{
	private  ExceptionEnum exceptionEnum;
}
