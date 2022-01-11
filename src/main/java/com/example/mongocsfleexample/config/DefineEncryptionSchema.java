package com.example.mongocsfleexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoJsonSchemaCreator;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.stereotype.Component;

import com.example.mongocsfleexample.SampleEntity;


@Component
public class DefineEncryptionSchema implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger( DefineEncryptionSchema.class );

    private final MongoMappingContext mappingContext;

    private final MongoOperations mongoTemplate;


    public DefineEncryptionSchema( MongoMappingContext mappingContext, MongoOperations mongoTemplate ) {
        this.mappingContext = mappingContext;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void run( ApplicationArguments args ) {
        MongoJsonSchemaCreator schemaCreator = MongoJsonSchemaCreator.create( mappingContext );
        MongoJsonSchema sampleSchema = schemaCreator.filter( MongoJsonSchemaCreator.encryptedOnly() )
                .createSchemaFor( SampleEntity.class );
        logger.info( sampleSchema.schemaDocument().toJson() );
        if( !mongoTemplate.collectionExists( SampleEntity.class ) )
            mongoTemplate.createCollection( SampleEntity.class, CollectionOptions.empty().schema( sampleSchema ) );
    }
}
