package com.leyou.upload.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.DateUtils;
import com.leyou.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-02-14:02
 */
@Service
@Slf4j
public class UploadService
{
	private static final List ALLOW_TYPES = Arrays.asList("JPG", "PNG", "GIF");
	public String uploadImage(MultipartFile file)
	{
		try {
			//判断文件类型
			String fileName =  file.getOriginalFilename();
			String fileType = (fileName.substring(fileName.lastIndexOf(".") + 1)).toUpperCase();
			if (!ALLOW_TYPES.contains(fileType))
			{
				throw new LyException(ExceptionEnum.WRONG_FILE_TYPE);
			}
			//校验文件内容
			BufferedImage image = ImageIO.read(file.getInputStream());

			if (image == null)
			{
				throw new LyException(ExceptionEnum.WRONG_FILE_TYPE);
			}
			//准备文件存储路径
			String newFilePath =DateUtils.getYear()+"/"+DateUtils.getMonth()+"/"+DateUtils.getDay()+"/"+ UUIDUtils.getUUID() + "." + fileType;
			File targetFile = new File("G:\\upload\\images\\", newFilePath);
			File parentFile = targetFile.getParentFile();
			if (!parentFile.exists())
			{
				parentFile.mkdirs();
			}
			targetFile.createNewFile();
			//存储文件
			file.transferTo(targetFile);
			return "http://image.leyou.com/images/" + newFilePath;
		} catch (IOException e) {
			log.error("文件上传失败", e.getMessage());
			throw new LyException(ExceptionEnum.UPLOAD_FILE_FAILED);
		}

	}

}
