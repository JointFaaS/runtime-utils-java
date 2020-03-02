package jointfaas.runtime.storage;

import jointfaas.runtime.storage.cloud.StorageAli;

import java.util.Map;

public class StorageBuilder {
    public Storage NewStorageAliFromEnv() throws EnvException {
        Map<String,String> map = System.getenv();
        if (!map.containsKey("ALIYUN")) {
            throw new EnvException();
        }
        String ossEndpoint = map.get("ALIYUN_OSS_ENDPOINT");
        String accessKey = map.get("ALIYUN_ACCESS_KEY");
        String accessSecret = map.get("ALIYUN_ACCESS_SECRET");
        String bucketName = map.get("ALIYUN_OSS_BUCKETNAME");
        StorageAli storageAli = new StorageAli(ossEndpoint, accessKey, accessSecret, bucketName);
        return storageAli;
    }

    public Storage NewStorageRedis() {
        Map<String,String> map = System.getenv();
        if (!map.containsKey("REDIS")) {
            throw new EnvException();
        }
        return null;
    }
}
