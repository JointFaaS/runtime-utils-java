package jointfaas.runtime.storage;

import java.security.KeyException;

public interface Storage {
    void put(String key, byte[] value) throws ClientException;

    byte[] get(String key) throws ClientException, KeyException;

    void del(String key) throws ClientException, KeyException;
}
