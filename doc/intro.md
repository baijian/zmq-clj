# Introduction to zmq-clj

## Intro

*var* is global-mutable
+var+ is a global-constant

创建一个线程bind`inproc://test`作为server用来recv消息,
创建5个线程connect`inproc://test`作为client用来循环发送消息.

## Concurrent Programming

Clojure is designed for concurrentcy.
Clojure simplifies multi-threaded programming in serveral ways.
Because the core data structures are immutable, they can be shared readily
between threads.

### Future

Future is a mechanism to communicate result of asynchronous computation
from producer to comsumer(s).

Future is a container for a single value(ofcourse can be a map or list, but should
be immutable) and trying to dreference future before it is resolved blocks.
It can be resolved once.`Future` represents background computation.It is more like
an event we are waiting for.

```clojure
(let [sum (future (apply + (range 1e5)))]
  (println "Started...")
  (println "Result: " @sum))
```

### Promise

Promise is a thread-safe object that encapsulates immutable value.
This value might not be avaiable yet and can be delivered exactly once,
from any thread, later.If other thread tries to dereference a promise
before it is delivered, it will block calling thread.If promise is already
delivered, no blocking occurs. Promise can only be delivered once and can
never change its value once set.

```clojure
;answer is a promise var
(def answer (promise))

;dereference it using `@answer` or `(deref answer)`
;this will simply block,other thread must deliver value to it
@answer

;thread which is blocked will wake up after the answer has been delivered, and return 42
(deliver answer 42)
```

