/**
 * @author arashxr
 * @email oberon15th@gmail.com
 * @github <a href="https://github.com/Unrex5">Arius Github</a>
 */
package com.Arius.myNetwork.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
public class AesEncryption implements AttributeConverter<Object,String> {

    @Value("${encryption.key}")
    private  String SECRET_KEY;
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    private Cipher getEncryptCipher() {
        return encryptCipher;
    }

    private Cipher getDecryptCipher() {
        return decryptCipher;
    }

    @PostConstruct
    public void initializeKey(){
        String secretCypher = "AES";
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(), secretCypher);
        try {
            encryptCipher = Cipher.getInstance(secretCypher);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance(secretCypher);
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String convertToDatabaseColumn(Object attribute) {

        if(attribute == null) return null;
        // the serializer and deserializer must be from the same import class or will get error in outputStream
        byte[] bytes = org.apache.commons.lang3.SerializationUtils.serialize((Serializable) attribute);
            try {
                assert bytes != null;
                return Base64.getEncoder().encodeToString(getEncryptCipher().doFinal(bytes));
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        if(dbData == null) return null;
        try {
     // the serializer and deserializer must be from the same import class or will get error in outputStream
            byte[]  bytes = getDecryptCipher().doFinal(Base64.getDecoder().decode(dbData));
            return org.apache.commons.lang3.SerializationUtils.deserialize(bytes);

        } catch (IllegalBlockSizeException | BadPaddingException  e) {
            throw new RuntimeException(e);
        }
    }
}
