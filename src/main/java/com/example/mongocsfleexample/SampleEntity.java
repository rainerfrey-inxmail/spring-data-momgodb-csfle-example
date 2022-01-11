package com.example.mongocsfleexample;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Encrypted;

import static org.springframework.data.mongodb.core.EncryptionAlgorithms.AEAD_AES_256_CBC_HMAC_SHA_512_Deterministic;


@Document( collection = "sample" )
@Encrypted( keyId = "#{ mongocrypt.keyId(#target) }", algorithm = AEAD_AES_256_CBC_HMAC_SHA_512_Deterministic )
public class SampleEntity {
    @Encrypted
    private String email;


    public String getEmail() {
        return email;
    }


    public void setEmail( String email ) {
        this.email = email;
    }
}
