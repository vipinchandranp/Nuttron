package com.nuttron.wind.dao;

import java.util.List;

import com.nuttron.wind.dao.util.FilterNode;

public interface EntityDAO {

	@SuppressWarnings("rawtypes")
	public List getEntity(FilterNode filterNode) throws Exception;

	public FilterNode getFilterNodeFromEntity(Object entityObject, Object parentEntityObject,
			FilterNode childFilterNode) throws Exception;

	@SuppressWarnings("rawtypes")
	public List getEntity(Object entityObject) throws Exception;

}
