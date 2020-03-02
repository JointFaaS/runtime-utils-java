package jointfaas.runtime.storage.cloud;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import jointfaas.runtime.storage.KeyException;

import java.io.*;

public class StorageAli implements jointfaas.runtime.storage.Storage {
    private String bucketName;
    private OSS ossClient;

    public StorageAli(String endpoint, String accessKeyId, String accessKeySecret, String bucketName) {
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        this.bucketName = bucketName;
    }



    @Override
    public void put(String key, byte[] value) throws jointfaas.runtime.storage.ClientException {
        try {
            ossClient.putObject(bucketName, key, new ByteArrayInputStream(value));
        }catch (OSSException oe) {
            throw new jointfaas.runtime.storage.KeyException();
        }catch (ClientException ce) {
            throw new jointfaas.runtime.storage.ClientException();
        }
    }

    @Override
    public byte[] get(String key) {
        try {
            OSSObject ossObject = ossClient.getObject(bucketName, key);
            InputStream content = ossObject.getObjectContent();
            byte[] bytes = IOUtils.readStreamAsByteArray(content);
            content.close();
            return bytes;
        }catch (OSSException oe) {
            throw new jointfaas.runtime.storage.KeyException();
        }catch (ClientException ce) {
            throw new jointfaas.runtime.storage.ClientException();
        }catch (IOException e) {
            // is it possible?
            return new byte[0];
        }
    }


    @Override
    public void del(String key) throws jointfaas.runtime.storage.ClientException, KeyException {
        try {
            ossClient.deleteObject(bucketName, key);
        }catch (OSSException oe) {
            throw new jointfaas.runtime.storage.KeyException();
        }catch (ClientException ce) {
            throw new jointfaas.runtime.storage.ClientException();
        }
    }
}
