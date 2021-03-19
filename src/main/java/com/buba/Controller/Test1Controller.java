package com.buba.Controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test1Controller {
    static Logger logger = LoggerFactory.getLogger("LOGGER");

    public static void main(String[] args) {
        logger.debug("开始================");
//        test1();
//        test2();
//        List<Object> objects = test3();
//        System.out.println(objects.get(0)+""+objects.get(1));
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
        test9();
        logger.debug("结束================");
    }

    private static void test9() {
        if(!StringUtils.equalsIgnoreCase("false","true")){
            System.out.println("123");
        }
    }

    private static void test8() {
        Integer s = 3;
        Integer ss = 3;
        if(s>=ss){
            System.out.println("s大或一样大");
        }else{
            System.out.println("ss大");
        }
    }

    private static void test7() {
        BigDecimal s = BigDecimal.TEN;
        BigDecimal s1 = BigDecimal.TEN;
        BigDecimal s2 = BigDecimal.TEN;
        BigDecimal bigDecimal = s.add(s1).multiply(s2).setScale(2);
        System.out.println(bigDecimal);
    }

    private static void test6() {
        String s = "123";
        String ss = "456";
        String s1 = "123";
        String s2 = "456";
        if(s.equals(s1)){
            System.out.println("123");
        }else if(ss.equals(s2)){
            System.out.println("456");
        }else{
            System.out.println("789");
        }
    }

    private static void test5() {
        int s1 = 1;
        int s2 = 2;
        if(s1 != 1 || s2 != 2){
            System.out.println("123");
        }else{
            System.out.println("456");
        }

    }

    private static void test4() {
        String str = "上传条数据!新增条数据!修改条数据!失败条数据!";
        String[] split = str.split("!");
        System.out.println(split[0]);
    }

    private static List<Object> test3() {
        Integer i = 0;
        Integer j = 0;
        List<Object> objects = new ArrayList<>();
        System.out.println(i++);
        System.out.println(++j);
        objects.add(i);
        objects.add(j);
        return objects;
    }


    private static void test2() {
        try {
            //建立一个File对象
            File file = new File("C:\\Users\\Self-made\\Desktop\\TEST.txt");
            //判断该文件的所属文件夹存不存在，不存在则创建文件夹
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //创建字符流（使用字节流比较麻烦）
            FileWriter fw = new FileWriter(file);
            //判断file是否存在
            if(!file.exists()){
                //如果不存在file文件，则创建
                file.createNewFile();
                fw.write("[{\"name\":\"你好\"}]");
            }else{
                //如果存在该file，可以根据情况来重写该file文件的内容
                fw.write("[{\"name\":\"aa\"}]");
            }
            //这里要说明一下，write方法是写入缓存区，并没有写进file文件里面，要使用flush方法才写进去
            fw.flush();
            //关闭资源
            fw.close();
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }

    private static void test1() {
        String url="C:\\Users\\Self-made\\Desktop\\TEST.xls";
        FileInputStream fs;
        try {
            fs = new FileInputStream(url);
            POIFSFileSystem ps=new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
            HSSFWorkbook wb=new HSSFWorkbook(ps);
            HSSFSheet sheet=wb.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表
            HSSFRow row=sheet.getRow(0);
            int hang=0;
            if("".equals(row)||row==null){
                hang=0;
            }else{
                hang=sheet.getLastRowNum();
                hang=hang+1;
            }
            //分别得到最后一行的行号，和一条记录的最后一个单元格
            FileOutputStream out=new FileOutputStream(url);  //向d://test.xls中写数据
            row=sheet.createRow((short)(hang)); //在现有行号后追加数据
            row.createCell(0).setCellValue("安徽"); //设置第一个（从0开始）单元格的数据
            out.flush();
            wb.write(out);
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void exportTxt(HttpServletResponse response, String text){
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition","attachment;filename="
                + genAttachmentFileName( "文件名称", "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + ".txt");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(text.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            //LOGGER.error("导出文件文件出错:{}",e);
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception e) {
                //LOGGER.error("关闭流对象出错 e:{}",e);
            }
        }
    }

//防止中文文件名显示出错

    public  String genAttachmentFileName(String cnName, String defaultName) {
        try {
            cnName = new String(cnName.getBytes("gb2312"), "ISO8859-1");
        } catch (Exception e) {
            cnName = defaultName;
        }
        return cnName;
    }
}
