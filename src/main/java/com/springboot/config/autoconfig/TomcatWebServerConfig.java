package com.springboot.config.autoconfig;

import com.springboot.config.ConditionalMyOnClass;
import com.springboot.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

//@Configuration
@MyAutoConfiguration
//@Conditional(TomcatWebServerConfig.TomcatCondition.class)
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
    @Bean("tomcatWebServerFactory") // User 구성 정보에서 제외필요. 패키지를 옮기면 ComponentScan 대상 제외됨
    @ConditionalOnMissingBean // Configuration class level 라이브러리 포함여부 체크 후 Bean level에서 같은 타입의 bean을 구성정보로 만들었는지 체크 후 없을경우 생성
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    /*static class TomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // return false;
            // 라이브러리가 포함되었는지 체크 : 클래스 관련 Util
            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
        }
    }*/
}
