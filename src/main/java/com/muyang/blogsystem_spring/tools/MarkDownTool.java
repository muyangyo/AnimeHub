package com.muyang.blogsystem_spring.tools;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建于 IntelliJ IDEA.
 * 描述：
 * User: 沐阳Yo
 * Date: 2024/2/15
 * Time: 10:39
 */
@Slf4j
public class MarkDownTool {
    public static String getDigest(String content) {
        char[] chars = content.toCharArray();
        int start = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#') {
                if (chars[i + 1] == '#') {
                    i++;
                    if (chars[i + 1] == '#') {
                        break; //三级标题大概率不是主题,使用直接舍弃
                    }
                }
                start = ++i;
                break;
            }
        }
        if (start == -1) {
            log.warn("一篇文章的摘要生成失败,原因是缺少一级标题或者二级标题");
            return "该篇文章的摘要生成失败,请至少写一个一级标题或者二级标题";
        }
        int end = start;
        while (end < chars.length) {
            if (chars[end] == '#' || chars[end] == '\n') {
                break;
            }
            end++;
        }
        //限制20个字符,超过要舍弃掉
        if (end >= 30) {
            end = 28;
        }
        String substring = content.substring(start, end);
        if (end == 28) {
            substring = substring + "...";
        }
        log.info("成功生成一篇文章的摘要为'{}'", substring);
        return substring;
    }

}
