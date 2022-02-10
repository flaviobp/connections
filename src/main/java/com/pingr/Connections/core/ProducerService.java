package com.pingr.Connections.core;

import com.pingr.Connections.core.events.FriendshipEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Value(value = "${topics.fshp_establd}")
    private String friendshipEstablishedTopic;

    @Value(value = "${topics.fshp_deleted}")
    private String friendshipDeletedTopic;

    @Autowired // injeção de dependências
    private KafkaTemplate<String, Object> template;

    public void emitFriendshipEstablishedEvent(Long userid, Long friendid) {
        this.template.send(
                this.friendshipEstablishedTopic,
                FriendshipEvent.of(this.friendshipEstablishedTopic, userid, friendid));
    }

    public void emitFriendshipDeletedEvent(Long userid, Long friendid) {
        this.template.send(
                this.friendshipDeletedTopic,
                FriendshipEvent.of(this.friendshipDeletedTopic, userid, friendid));
    }
}