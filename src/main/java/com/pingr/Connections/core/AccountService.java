package com.pingr.Connections.core;

import com.pingr.Connections.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    private final AccountRepository repo;
    private final ProducerService producer;

    @Autowired
    public AccountService(AccountRepository repo, ProducerService producer) {
        this.repo = repo;
        this.producer = producer;
    }

    public Account createAccount(Account account) {
        return this.repo.save(account);
    }

    public void deleteAccount(Long id) {
        this.repo.deleteById(id);
    }

    public boolean stablishFriendshipBetween(Long userid, Long friendid) {
        Optional<Account> userById = this.repo.findById(userid);
        if (userById.isEmpty()) {
            throw new EntityNotFoundException("account", userid);
        }

        Optional<Account> friendById = this.repo.findById(friendid);
        if (friendById.isEmpty()) {
            throw new EntityNotFoundException("account", friendid);
        }

        Account accountUser = userById.get();
        Account accountFriend = friendById.get();
        if(accountUser.getFriends().stream().anyMatch(x -> x.getId() == friendid))
            throw new IllegalArgumentException("Friendship violates restrictions" + "[account: " + userid + "]");

        accountUser.getFriends().add(accountFriend);

        this.repo.save(accountUser);

        this.producer.emitFriendshipEstablishedEvent(userid, friendid);

        return true;
    }

    public boolean cancelFriendshipBetween(Long userid, Long friendid) {
        Optional<Account> userById = this.repo.findById(userid);
        if (userById.isEmpty()) {
            throw new EntityNotFoundException("account", userid);
        }

        Optional<Account> friendById = this.repo.findById(friendid);
        if (friendById.isEmpty()) {
            throw new EntityNotFoundException("account", friendid);
        }

        Account accountUser = userById.get();
        Account accountFriend = userById.get();
        if(accountUser.getFriends().stream().noneMatch(x -> x.getId() == friendid))
            throw new IllegalArgumentException("Friendship violates restrictions" + "[account: " + userid + "]");

        accountUser.getFriends().removeIf(x -> x.getId() == friendid);

        this.repo.save(accountUser);

        this.producer.emitFriendshipDeletedEvent(userid, friendid);

        return true;
    }

    public Set<Account> listAllFriends(Long id){
        Optional<Account> userById = this.repo.findById(id);
        if (userById.isEmpty()) {
            throw new EntityNotFoundException("account", id);
        }

        Account accountUser = userById.get();
        return accountUser.getFriends();
    }



}
