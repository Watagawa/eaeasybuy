package cn.easybuy;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;


public class CodeGenerator {

    @Test
    public void genCode() {


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //得到当前项目的目录
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("黄嘉豪");
        //生成后是否打开资源管理器
        gc.setOpen(false);
        //重新生成时文件是否覆盖
        //gc.setFileOverride(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.10.3:3306/easybuy?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Rootsjk.");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //模块名
        //  pc.setModuleName(scanner("模块名"));
        pc.setParent("cn.easybuy");
        pc.setController("controller");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略 改成驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略  改成驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // lombok 模型
        strategy.setEntityLombokModel(true);
        //restful api风格控制器
        strategy.setRestControllerStyle(true);
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //设置表前缀不生成
        strategy.setTablePrefix("easybuy" + "_");
        mpg.setStrategy(strategy);


        // 执行
        mpg.execute();
    }
}