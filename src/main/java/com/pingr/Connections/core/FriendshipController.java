package com.pingr.Connections.core;

import com.pingr.Connections.core.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/friendships")
public class FriendshipController {

    private final AccountService service;

    @Autowired
    public FriendshipController(AccountService service) {
        this.service = service;
    }

    @PostMapping(path = {"/{userid}/{friendid}"})
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addFriends(@PathVariable("userid") Long userid, @PathVariable("friendid") Long friendid){
        return this.service.stablishFriendshipBetween(userid, friendid);
    }

    @DeleteMapping(path ={"/{userid}/{friendid}"})
    @ResponseStatus(HttpStatus.OK)
    public boolean removeFriends(@PathVariable("userid") Long userid, @PathVariable("friendid") Long friendid){
        return this.service.cancelFriendshipBetween(userid, friendid);
    }
}
