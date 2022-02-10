package com.pingr.Connections.core.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.Map;

@JsonSerialize
public class FriendshipEvent {

    @JsonProperty
    private final String eventType;

    @JsonProperty
    private final Long accountId;

    @JsonProperty
    private final Map<String, Object> payload;

    private FriendshipEvent(String eventType, Long accountId, Map<String, Object> payload) {
        this.eventType = eventType;
        this.accountId = accountId;
        this.payload = payload;
    }

    public static FriendshipEvent of(String eventType, Long userid, Long friendid) {
        Map<String, Object> accountMapView = new HashMap<>();

        accountMapView.put("userid", userid);
        accountMapView.put("friendid", friendid);

        return new FriendshipEvent(
                eventType,
                userid,
                accountMapView
        );
    }

}
