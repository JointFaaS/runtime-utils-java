package jointfaas.runtime.storage.uniform;

import jointfaas.runtime.storage.ClientException;
import redis.clients.jedis.Jedis;

import java.security.KeyException;

public class StorageRedis implements jointfaas.runtime.storage.Storage {
    private Jedis jedis;

    public StorageRedis(String host) {
        this.jedis = new Jedis(host);
    }

    @Override
    public void put(String key, byte[] value) throws ClientException {
        jedis.set(key.getBytes(), value);
    }

    @Override
    public byte[] get(String key) throws ClientException, KeyException {
        return jedis.get(key.getBytes());
    }

    @Override
    public void del(String key) throws ClientException, KeyException {
        jedis.del(key);
    }
}
