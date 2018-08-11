package com.nhatquang.builder;

import com.google.common.collect.Lists;
import com.nhatquang.form.BTCCriteria;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Quang Nguyen
 */
public class QueryBuilder {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String build(BTCCriteria criteria) {
        List<String> queryList = Lists.newArrayList();
        if (criteria != null) {
            if (StringUtils.isNotEmpty(criteria.getClientId())) {
                queryList.add(String.format("{clientIdentifier: \"%s\"}", criteria.getClientId()));
            }
            if (StringUtils.isNotEmpty(criteria.getAddress())) {
                queryList.add(String.format("{btcAddress: \"%s\"}", criteria.getAddress()));
            }
            if (StringUtils.isNotEmpty(criteria.getCountryCode())) {
                queryList.add(String.format("{countryCode: \"%s\"}", criteria.getCountryCode().toUpperCase()));
            }
            if (StringUtils.isNotEmpty(criteria.getItem())) {
                queryList.add(String.format("{item: {$regex: \"%s\", $options: 'im'}}",
                        Pattern.compile(String.format(".*%s.*", criteria.getItem()))));
            }
            if (StringUtils.isNotEmpty(criteria.getAvatar())) {
                queryList.add(String.format("{avatar: {$regex: \"%s\", $options: 'im'}}",
                        Pattern.compile(String.format(".*%s.*", criteria.getAvatar()))));
            }
            if (criteria.getFromDate() != null) {
                String fromDateStr = DateTimeFormatter.ofPattern(DATE_FORMAT).format(criteria.getFromDate().atTime(00, 00, 00));
                queryList.add(String.format("{date: {$gte: {$date: \"%s\"}}}",
                        fromDateStr));
            }
            if (criteria.getToDate() != null) {
                String toDateStr = DateTimeFormatter.ofPattern(DATE_FORMAT).format(criteria.getToDate().atTime(23, 59, 00));
                queryList.add(String.format("{date: {$lte: {$date: \"%s\"}}}",
                        toDateStr));
            }

            if (queryList.size() > 1) {
                return String.format("{$and: [%s]}", StringUtils.join(queryList, ','));
            } else if (queryList.size() == 1) {
                return queryList.get(0);
            } else {
                return "{}";
            }
        }
        return null;
    }
}
