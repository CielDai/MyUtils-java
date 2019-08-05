package com.sakura.utils.beancopy;

import com.tuyang.beanutils.BeanCopyUtils;

/**
 * Description: 使用yangtu222的beanCopy工具
 *
 * github: https://github.com/yangtu222/BeanUtils
 *
 * @author sakura
 * <p>
 * Date: 2019-07-12 3:56 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public class BeanUtils {


    public static void main(String[] args) {
        BeanCopyUtils.copyBean(new Object(), Object.class);
    }
}
