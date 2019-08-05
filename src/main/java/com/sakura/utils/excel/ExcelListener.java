package com.sakura.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

/**
 * Description: 未实现
 *
 * @author sakura
 * <p>
 * Date: 2019-07-23 5:36 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public class ExcelListener extends AnalysisEventListener {

    @Override
    public void invoke(Object student, AnalysisContext analysisContext) {
        System.out.println(JSON.toJSONString(student));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("全部处理完成");
    }
}
