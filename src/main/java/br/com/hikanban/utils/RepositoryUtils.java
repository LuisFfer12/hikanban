package br.com.hikanban.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("rawtypes")
public class RepositoryUtils {

	/**
	 * Append query
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery) {
		return currentQuery.append(addQuery); 
	}
	
	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param value the value to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery, Boolean value) {
		if (value != null) {
			currentQuery.append(addQuery);
		}
		return currentQuery;
	}

	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param value the value to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery, String value) {
		if (StringUtils.isNotBlank(value)) {
			currentQuery.append(addQuery);
		}
		return currentQuery;
	}

	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param value the value to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery, Integer value) {
		if (value != null) {
			currentQuery.append(addQuery);
		}
		return currentQuery;
	}

	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param value the value to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery, Long value) {
		if (value != null) {
			currentQuery.append(addQuery);
		}
		return currentQuery;
	}

	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param value the value to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery, BigDecimal value) {
		if (value != null) {
			currentQuery.append(addQuery);
		}
		return currentQuery;
	}

	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param value the value to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery, Optional value) {
        if (value != null && value.isPresent()) {
            currentQuery.append(addQuery);
        }
        return currentQuery;
    }

	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param values the list of values to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQuery(StringBuilder currentQuery, String addQuery, List<Integer> values) {
		if (CollectionUtils.isNotEmpty(values)) {
			currentQuery.append(addQuery);
		}
		return currentQuery;
	}
	
	/**
	 * Append query if value is not null or empty
	 *
	 * @param currentQuery the current {@link StringBuilder} of query
	 * @param addQuery the query to append in current query
	 * @param values the list of values to be verified
	 * @return The current query updated
	 */
	public static StringBuilder appendQueryList(StringBuilder currentQuery, String addQuery, List<Long> values) {
		if (CollectionUtils.isNotEmpty(values)) {
			currentQuery.append(addQuery);
		}
		return currentQuery;
	}

	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, Integer paramValue) {
		if (paramValue != null) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}

	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, Long paramValue) {
		if (paramValue != null) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}

	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, BigDecimal paramValue) {
		if (paramValue != null) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}

	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, String paramValue) {
		if (StringUtils.isNotBlank(paramValue)) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}

	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, Boolean paramValue) {
		if (paramValue != null) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}

	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, Set<Integer> paramValue) {
		if (CollectionUtils.isNotEmpty(paramValue)) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}

	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, List<Integer> paramValue) {
		if (CollectionUtils.isNotEmpty(paramValue)) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}
	
	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameterList(Query query, String paramName, List<Long> paramValue) {
		if (CollectionUtils.isNotEmpty(paramValue)) {
			query.setParameter(paramName, paramValue);
		}
		return query;
	}
	
	/**
	 * Add the parameter in query
	 *
	 * @param query the current query
	 * @param paramName the parameter name
	 * @param paramValue the parameter value
	 * @return The query updated
	 */
	public static Query setParameter(Query query, String paramName, Optional paramValue) {
		if (paramValue != null && paramValue.isPresent()) {
			query.setParameter(paramName, paramValue.get());
		}
		return query;
	}
}