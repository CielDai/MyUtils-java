package com.sakura.utils.excel.test;

import com.alibaba.fastjson.JSON;
import com.sakura.utils.excel.ExcelUtils;

import java.io.*;
import java.util.List;

/**
 * Description:
 *
 * @author sakura
 * <p>
 * Date: 2019-07-23 10:35 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public class Test {


    public static void main(String[] args) throws IOException {

        OutputStream out = new FileOutputStream("/Users/dhf/Desktop/test2.xlsx");
        File file = new File("/Users/dhf/Desktop/test2.xlsx");

        boolean write = ExcelUtils.write(out, DataUtil.createTestListJavaMode(), Student.class);
        if (write) {
            List<Student> read = ExcelUtils.read(new FileInputStream(file), Student.class, 2);
            System.out.println(JSON.toJSONString(read));
        }

    }

}
