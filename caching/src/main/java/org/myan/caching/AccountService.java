package org.myan.caching;

import org.myan.caching.support.CacheReload;
import org.myan.caching.support.Cacheable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
@Service
public class AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Resource
    private AccountCacheManager<String, Account> cm;

    public Account getAccountByName(String name) {
        Account result = cm.get(name);
        if(result != null){
            logger.info("Get data from cache...");
            return result;
        }
        // now get from db;
        Optional<Account> optional = getFromDB(name);
        if(!optional.isPresent())
            throw new IllegalStateException(String.format("Can not find data for name:%s", name));
        Account account = optional.get();
        cm.updateCache(name, account);// sync cache.
        return account;
    }

    @Cacheable("accounts")
    public Account getAccountById(int id) {
//        Account result = cm.get(String.valueOf(id));
//        if(result != null){
//            logger.info("Get data from cache...");
//            return result;
//        }
        // now get from db;
        Optional<Account> optional = getFromDB(id);
        if(!optional.isPresent())
            throw new IllegalStateException(String.format("Can not find data for id:%d", id));
        //cm.updateCache(String.valueOf(id), account);// sync cache.
        return optional.get();
    }

    private Optional<Account> getFromDB(int id) {
        logger.info("Querying data from database...");
        return Optional.of(new Account(id));
    }

    private Optional<Account> getFromDB(String name) {
        logger.info("Querying data from database...");
        return Optional.of(new Account(name));
    }

    public void reload() {
        cm.evictAll();
    }

    @CacheReload("accounts")
    public void reloadCacheObject() {

    }

}
