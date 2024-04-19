//package com.csp.cloud.user.common;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * Mybatis code generator
// *
// * @author chensiping
// * @since 2019-10-23
// **/
//public class MybatisGenerator {
//
//    private static final String MODULE_PATH = "/micro-user";
//
//    private static final String OUTPUT_DIR = MODULE_PATH + "/src/main/java";
//    private static final String AUTHOR = "chensiping";
//    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/micro_user?serverTimezone=UTC&characterEncoding=utf8&&allowMultiQueries=true";
//    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "66666@csp";
//
//    public static void main(String[] args) {
//
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + OUTPUT_DIR);
//        gc.setAuthor(AUTHOR);
//        gc.setOpen(false); // 是否打开输出目录，默认为true
//        gc.setFileOverride(false); // 是否覆盖已有文件
//        gc.setActiveRecord(false);// 开启 activeRecord 模式
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(true);// XML columnList
//        gc.setDateType(DateType.ONLY_DATE);
//
//        // 自定义文件名，%s 会自动填充表实体属性
//        gc.setControllerName("%sController");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setDriverName(DB_DRIVER);
//        dsc.setUrl(DB_URL);
//        dsc.setUsername(DB_USER);
//        dsc.setPassword(DB_PASSWORD);
//        mpg.setDataSource(dsc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel); // 表映射到实体的命名策略
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel); // 表字段映射到实体的命名策略，未指定按照 naming 执行
//        strategy.setInclude(scanner("表名")); // 需要生成的表，如果不指定就生成全部的表
//        strategy.setEntityLombokModel(true); // 是否为lombok模型
//        strategy.setRestControllerStyle(true); // 是否生成 @RestController 控制器
//        strategy.setEntityTableFieldAnnotationEnable(true); // 是否生成实体时，生成字段注解
//        mpg.setStrategy(strategy);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.csp");
//        pc.setModuleName("cloud.user");
//        pc.setController("controller");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setMapper("mapper");
//        pc.setEntity("model.entity");
//        pc.setXml("mapper");
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件目录：指定生成的mapper.xml文件输出位置
//                return projectPath + MODULE_PATH + "/src/main/resources/mybatis/mapper/"
//                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setXml(null); // 关闭默认 xml 生成
//        mpg.setTemplate(templateConfig);
//
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        // 执行生成
//        mpg.execute();
//    }
//
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    private static String scanner(String tip) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入" + tip + "：");
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//}
