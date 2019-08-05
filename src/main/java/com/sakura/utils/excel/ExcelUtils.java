package com.sakura.utils.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.tuyang.beanutils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Description:
 *
 * @author sakura
 * <p>
 * Date: 2019-07-22 5:57 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
@Slf4j
public class ExcelUtils {

    public static <T extends BaseRowModel> List<T> read(InputStream in, Class<T> clazz) {
        return read(in, clazz, 1);
    }

    public static <T extends BaseRowModel> List<T> read(InputStream in, Class<T> clazz, int startRow) {

        List<Object> list = EasyExcelFactory.read(in, new Sheet(1, startRow, clazz));

        for (Object o : list) {
            System.out.println(JSON.toJSONString(o));
        }
        try {
            in.close();
        } catch (IOException e) {
            log.error("资源关闭失败 {}", e.getMessage());
        }
        return BeanCopyUtils.copyList(list, clazz);
    }


    public static <T extends BaseRowModel> boolean write(OutputStream out, List<T> data, Class<T> clazz) {

        ExcelWriter writer = EasyExcelFactory.getWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet = new Sheet(1, 1, clazz);

        writer.write(data, sheet);

        try {
            writer.finish();
            out.close();
        } catch (IOException e) {
            log.error("资源关闭失败 {}", e.getMessage());
        }

        return true;
    }

}
