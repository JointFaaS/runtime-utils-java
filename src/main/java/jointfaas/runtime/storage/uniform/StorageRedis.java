package jointfaas.runtime.storage.uniform;

import jointfaas.runtime.storage.ClientException;

import java.security.KeyException;

public class StorageRedis implements jointfaas.runtime.storage.Storage {

    @Override
    public void put(String key, byte[] value) throws ClientException {

    }

    @Override
    public byte[] get(String key) throws ClientException, KeyException {
        return new byte[0];
    }

    @Override
    public void del(String key) throws ClientException, KeyException {

    }
}
