package com.gavin.study.mysqlcluster.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.gavin.study.mysqlcluster.common.base.dao.page.SearchBean;
import com.gavin.study.mysqlcluster.common.exception.InsuranceException;
import com.gavin.study.mysqlcluster.common.messagecode.MsgDescription;
import com.gavin.study.mysqlcluster.common.utils.GeneralUtils;

/**
 * Controller基类，所有的Controller必须要继承此类
 * <p/>
 * 
 * @author <a href="mailto:xcai@isoftstone.com">Alvin Ai</a>
 * @version Date: Oct 29, 2014 8:50:15 PM
 * @serial 1.0
 * @since Oct 29, 2014 8:50:15 PM
 */
public class BaseController {

	public static final String DEFAULT_JSON_DATA = "aaData";

	public static final String DEFAULT_JSON_TOTAL_PROPERTY = "iTotalRecords";
	
	public static final String DEFAULT_JSON_SECHO = "sEcho";
	
	public static final String DEFAULT_JSON_RECORDSFILTERED = "iTotalDisplayRecords";

	public static final String DEFAULT_JSON_MESSAGE = "message";

	public static final String DEFAULT_JSON_SUCCESS = "success";

	/**
	 * 
	 * 更具异常获取异常信息
	 * <p/>
	 * 
	 * @author Alvin Ai
	 * @date Oct 29, 2014 8:54:03 PM
	 * @param e
	 * @return
	 */
	protected String getMessage(InsuranceException e) {
		return MsgDescription.getMsgDesc(e.getErrorCode());
	}

	/**
	 * 
	 * 更具错误码获取错误信息
	 * <p/>
	 * 
	 * @author Alvin Ai
	 * @date Oct 29, 2014 8:54:24 PM
	 * @param code
	 * @return
	 */
	protected String getMessage(int code) {
		return MsgDescription.getMsgDesc(code);
	}

	protected JSONObject toJSONResult(long count, List data) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_DATA, data);
		return result;
	}
	
	
	protected JSONObject toJSONResult(long count, List data,int sEcho) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_RECORDSFILTERED, count);
		result.put(DEFAULT_JSON_DATA, data);
		result.put(DEFAULT_JSON_SECHO, sEcho);
		return result;
	}

	protected JSONObject toJSONResult(boolean success, String message) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		if (!GeneralUtils.isNullOrZeroLenght(message)) {
			result.put(DEFAULT_JSON_MESSAGE, message);
		}
		return result;
	}

	protected JSONObject toJSONResult(boolean success) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		return result;
	}

	protected JSONObject toJSONResult(boolean success, Object data) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_DATA, data);
		return result;
	}

	protected Map<String, Object> toJSONResultMap(boolean success, Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_DATA, data);
		return result;
	}

	protected Map<String, Object> toJSONResultMap(boolean success, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_MESSAGE, msg);
		return result;
	}

	protected JSONArray toJSONArrayResult(List data) {
		return JSONArray.fromObject(data);
	}

	protected Pageable convert2Pageable(int start, int limit) {
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		Pageable pageable = new PageRequest(start / limit, limit);
		return pageable;
	}

	/**
	 * 根据前台的param参数，返回searchbean对象
	 * <p/>
	 * 
	 * @author Alvin Ai
	 * @date Oct 29, 2014 8:52:11 PM
	 * @param param
	 */
	protected SearchBean[] convert2SearchBean(String param) {
		if (GeneralUtils.isNullOrZeroLenght(param)) {
			return new SearchBean[] {};
		}

		String[] paramStrs = param.split(",");

		List<SearchBean> list = new ArrayList<SearchBean>();
		for (String paramStr : paramStrs) {
			String[] search = paramStr.split(":");
			// 如果没有填写值，则不生成searchbean
			if (search.length < 3 || GeneralUtils.isNullOrZeroLenght(search[1])) {
				continue;
			}

			SearchBean searchBean = new SearchBean(search[0], search[1],
					search[2]);
			list.add(searchBean);
		}
		return list.toArray(new SearchBean[] {});
	}

}
