package com.example.mongocsfleexample.config;

import java.util.Base64;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@ConfigurationProperties( "mongodb.encryption" )
public class MongodbEncryptionProperties {
    private String localMasterKey;

    private String keyVaultNamespace = "encryption.keyvault";

    private String dataKeyId;


    public String getLocalMasterKey() {
        return localMasterKey;
    }


    public void setLocalMasterKey( String localMasterKey ) {
        this.localMasterKey = localMasterKey;
    }


    public String getKeyVaultNamespace() {
        return keyVaultNamespace;
    }


    public void setKeyVaultNamespace( String keyVaultNamespace ) {
        this.keyVaultNamespace = keyVaultNamespace;
    }


    public String getDataKeyId() {
        return dataKeyId;
    }


    public void setDataKeyId( String dataKeyId ) {
        this.dataKeyId = dataKeyId;
    }


    public boolean isEncryptionConfigured() {
        return StringUtils.hasText( localMasterKey ) && StringUtils.hasText( dataKeyId );
    }


    public Map<String, Map<String, Object>> getLocalKmsProvider() {
        byte[] key = Base64.getDecoder().decode( localMasterKey );
        return Map.of( "local", Map.of( "key", key ) );
    }


    public String resolveKeyId( String targetEntity ) {
        return getDataKeyId();
    }
}
