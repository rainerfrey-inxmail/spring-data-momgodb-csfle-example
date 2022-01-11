package com.example.mongocsfleexample.config;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.data.spel.spi.Function;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


@Component
public class EncryptionExtension implements EvaluationContextExtension {

    private final MongodbEncryptionProperties properties;


    public EncryptionExtension( MongodbEncryptionProperties properties ) {
        this.properties = properties;
    }


    @Override
    public String getExtensionId() {
        return "mongocrypt";
    }


    @Override
    public Map<String, Function> getFunctions() {
        Method resolveKeyId = ReflectionUtils.findMethod( MongodbEncryptionProperties.class, "resolveKeyId", String.class );
        return Collections.singletonMap( "keyId", new Function( resolveKeyId, properties ) );
    }
}
