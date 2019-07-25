event-driven architecture --pros and cons
1.loose coupling
with the event-driven architecture ,we can achieve loose coupling between out service
2.transactions
on the other hand, in an architecture based on event we need to assume that we don't need ACID,instead of it is eventual consistency 
3.fault tolerance
as a sequence of not having transactions, fault tolerance become more important

Exchanges and exchange type
exchanges are AMPQ entities where message are sent,Exchange take a message and route it to zero or more queues.
the routing algorithm used depends on the exchange type and rules called binding.

1.direct exchange : based on routing ket
A direct exchange delivers messages to queues based on the message routing key,here how it works:
    ~ a queue binds to exchange with a routing key
    ~ when a new message with routing key R arrives at the direct exchange ,the exchange routes it to the queue if K = R
    
2.Fanout exchange  : like broadcast
a fanout exchange routes messages ot all of the queues that are bound to it and the routing key is ignored
Fanout exchanges are ideal for the broadcast routing of messages

3.topic exchange : match pattern between a message routing key and the pattern   
Topic exchanges route messages to one or many queues based on matching between a message routing key and the pattern than was used to bind a queue to an exchange

4.header exchange
a headers exchange is designed for routing on multiple attributes that are more easily expressed as message headers than a routing key. headers exchanges ignore the routing key key attribute. instead,the attribute used for routing are taken from the header attribute. A message is considered matching if the header equals the value specified on the binding.
 
it is possible to bind a queue to a header exchange using more than one header for matching 
