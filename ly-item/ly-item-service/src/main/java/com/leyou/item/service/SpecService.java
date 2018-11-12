package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.entity.SpecGruop;
import com.leyou.item.entity.SpecParam;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-03-15:00
 */
@Service
public class SpecService
{
	@Autowired
	private SpecGroupMapper specGroupMapper;
	@Autowired
	private SpecParamMapper specParamMapper;

	public List<SpecGruop> queryGroupByCid(Long cid)
	{
		SpecGruop specGruop = new SpecGruop();
		specGruop.setCid(cid);
		List<SpecGruop> gruops = specGroupMapper.select(specGruop);
		if (CollectionUtils.isEmpty(gruops))
		{
			throw new LyException(ExceptionEnum.WRONG_FILE_TYPE);
		}
		return gruops;
	}

	public void saveGroup(SpecGruop specGruop)
	{
		int count = specGroupMapper.insert(specGruop);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.SPEC_GROUP_SAVE_FAILED);
		}
	}

	public void modifyGroup(SpecGruop specGruop)
	{
		int count = specGroupMapper.updateByPrimaryKey(specGruop);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.SPEC_GROUP_UPDATE_FAILED);
		}
	}

	public void removeGroup(Long id)
	{
		int count = specGroupMapper.deleteByPrimaryKey(id);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.SPEC_GROUP_REMOVE_FAILED);
		}
	}

	public List<SpecParam>  queryParamsByGid(Long gid)
	{
		SpecParam specParam = new SpecParam();
		specParam.setGroupId(gid);
		List<SpecParam> list = specParamMapper.select(specParam);
		if (CollectionUtils.isEmpty(list))
		{
			throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FIND);
		}
		return list;
	}

	public void saveParam(SpecParam specParam)
	{
		int count = specParamMapper.insertSelective(specParam);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.SPEC_PARAM_SAVE_FAILED);
		}
	}

	public void modifyParam(SpecParam specParam)
	{
		int count = specParamMapper.updateByPrimaryKeySelective(specParam);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.SPEC_PARAM_UPDATE_FAILED);
		}
	}

	public void removeParam(Long pid)
	{
		int count = specParamMapper.deleteByPrimaryKey(pid);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.SPEC_PARAM_REMOVE_FAILED);
		}
	}

	public List<SpecParam> queryParamsList(Long cid, Long gid, Boolean searching)
	{
		SpecParam specParam = new SpecParam();
		specParam.setCid(cid);
		specParam.setGroupId(gid);
		specParam.setSearching(searching);
		List<SpecParam> list = specParamMapper.select(specParam);
		if (CollectionUtils.isEmpty(list))
		{
			throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FIND);
		}
		return list;
	}
}
