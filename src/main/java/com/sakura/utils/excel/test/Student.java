package com.sakura.utils.excel.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 *
 * @author sakura
 * <p>
 * Date: 2019-07-22 5:57 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseRowModel {

    @ExcelProperty(index = 0, value = {"姓名", "1"})
    private String name;
    @ExcelProperty(index = 1, value = {"姓名", "2"})
    private String age;
    @ExcelProperty(index = 2, value = {"性别"})
    private String gender;
//
//    private Date birthday;
//
//    private LocalDateTime createTime;
}
