package com.leyou.common.utils;

import java.util.UUID;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-02-14:57
 */
public class UUIDUtils
{
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
