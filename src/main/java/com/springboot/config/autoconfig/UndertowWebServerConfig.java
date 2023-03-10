package com.springboot.config.autoconfig;

import com.springboot.config.ConditionalMyOnClass;
import com.springboot.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

//@Configuration
@MyAutoConfiguration
//@Conditional(UndertowWebServerConfig.UndertowCondition.class) // Condition interface 구현한 class 지정 필수
@ConditionalMyOnClass("io.undertow.Undertow")
public class UndertowWebServerConfig {
    @Bean("undertowWebServerFactory") // @Bean을 통해 factory method 만들때 기본 이름은 메소드명 servletWebServerFactory. 이름 충돌 방지를 위해 지정
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory() {
        return new UndertowServletWebServerFactory();
    }

    /*static class UndertowCondition implements Condition { // 중첩 inner static class (보기 편하도록)
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) { // application 환경정보, annotation 메타데이터
            return ClassUtils.isPresent("io.undertow.Undertow", context.getClassLoader());
        }
    }*/
}
