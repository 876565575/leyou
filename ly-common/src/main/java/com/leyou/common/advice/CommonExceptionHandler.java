package com.leyou.common.advice;

import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-12:46
 */

@ControllerAdvice
public class CommonExceptionHandler
{
	@ExceptionHandler(LyException.class)
	public ResponseEntity<ExceptionResult> handlerException(LyException e)
	{
		return ResponseEntity.status(e.getExceptionEnum().getCode())
				.body(new ExceptionResult(e.getExceptionEnum()));
	}
}
