package vn.vela.sample.mongodb.service.impl;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import vn.vela.sample.mongodb.constant.NewConstant;
import vn.vela.sample.mongodb.dto.NewAddDto;
import vn.vela.sample.mongodb.dto.NewDto;
import vn.vela.sample.mongodb.service.NewService;

@Service
@Transactional
@Log4j2
public class NewServiceImpl implements NewService {

  private final String MONGO_DATABASE = "device_service";
  @Value("${spring.data.mongodb.host}")
  private String host;
  @Value("${spring.data.mongodb.port}")
  private int port;
  @Value("${spring.data.mongodb.database}")
  private String database;
  @Value("${spring.data.mongodb.username}")
  private String username;
  @Value("${spring.data.mongodb.password}")
  private String password;
  @Value(("${spring.data.mongodb.uri}"))
  private String uri;
  @Value("${spring.data.mongodb.authentication-database}")
  private String authen;

  @Override
  public NewDto addNewService(NewAddDto newAddDto) {
    MongoCollection<Document> collection = buildConnec(newAddDto.getAlias());
    //Check and create index for md5 column
    boolean hasMd5Index = false;
    ListIndexesIterable<Document> indexs = collection.listIndexes();
    for (Document index : indexs) {
      String indexName = index.getString("name");
      if (StringUtils.startsWithIgnoreCase(indexName, NewConstant.MD5)) {
        hasMd5Index = true;
      }
    }
    if (!hasMd5Index) {
      collection.createIndex(Indexes.ascending(NewConstant.MD5),
          new IndexOptions().unique(true));
    }
    String hashText = createNewTokenMd5(newAddDto.getAlias());
    Document documentCheck = collection.find(eq(NewConstant.MD5, hashText)).first();
    if (documentCheck != null) {
      log.warn("checksum have exist with object : " + documentCheck);
      return null;
    }
    // insert new collection, build by NewDto
    Document document = insert(collection, newAddDto);
    // Get detail one document

    return mappingNewDto(document);
  }

  private NewDto mappingNewDto(Document document) {
    return NewDto.builder().alias(document.getString(NewConstant.ALIAS))
        .author(document.getString(NewConstant.AUTHOR))
        .content(document.getString(NewConstant.CONTENT))
        .createdAt(document.getDate(NewConstant.CREATED_AT).toInstant().atZone(
            ZoneId.systemDefault()).toLocalDateTime()).id(document.get(NewConstant.ID).toString())
        .md5(document.getString(NewConstant.MD5)).updatedAt(
            document.getDate(NewConstant.UPDATED_AT).toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime())
        .title(document.getString(NewConstant.TITLE)).build();

  }

  private Document insert(MongoCollection collection, NewAddDto newAddDto) {
    Document document = new Document();
    document.append(NewConstant.TITLE, newAddDto.getTitle())
        .append(NewConstant.CONTENT, newAddDto.getContent())
        .append(NewConstant.AUTHOR, newAddDto.getAuthor())
        .append(NewConstant.ALIAS, newAddDto.getAlias())
        .append(NewConstant.CREATED_AT, new Date())
        .append(NewConstant.UPDATED_AT, new Date())
        .append(NewConstant.MD5, createNewTokenMd5(newAddDto.getAlias()));
    collection.insertOne(document);
    return document;
  }

  private String createNewTokenMd5(String alias) {
    String hashText = null;
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      byte[] bytesOfMessage = digest.digest(alias.getBytes(Charset.forName("UTF-8")));
      BigInteger number = new BigInteger(1, bytesOfMessage);
      hashText = number.toString(16);
      while (hashText.length() < 32) {
        hashText = String.format("0%s", hashText);
      }

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hashText;
  }


  /**
   * Build collection trong mongodb
   */
  private MongoCollection<Document> buildConnec(String alias) {
    ServerAddress serverAddress = new ServerAddress(host, port);
    MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
    MongoCredential mongoCredential = MongoCredential
        .createCredential(username, database, password.toCharArray());

    MongoClient client = new MongoClient(serverAddress, mongoCredential, mongoClientOptions);
    MongoDatabase database = client.getDatabase(MONGO_DATABASE);
    return database.getCollection(alias);
  }

}
