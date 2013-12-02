(ns zmq-clj.pushpull
  (:use [org.zeromq.clojure :as zmq])
  (:gen-class))

(defn work [socket]
  (let [workload (inc (rand-int 100))]
    (send- socket (.getBytes (str workload)))
    workload))

(defn -main[]
  (future
    (let [ctx (make-context 1)]
      (let [push (doto (make-socket ctx +downstream+)
                   (bind "tcp://*:5000"))
            sink (doto (make-socket ctx +downstream+)
                   (connect "tcp://*:5001"))]
        (send- sink (.getBytes "0"))
        (let [times (repeatedly 100 (partial work push))]
          (printf "Total expected cost: %d msec\n" (apply + times))))))
  (future
    (let [ctx (make-context 1)]
      (let [pull (doto (make-socket ctx +upstream+)
                   (connect "tcp://127.0.0.1:5000"))
            sender (doto (make-socket ctx +downstream+)
                     (connect "tcp://127.0.0.1:5001"))]
        (while (not (.. Thread currentThread isInterrupted))
          (let [string (recv pull)]
            (println (str "Pull Recv(5000):" (String. string)))
            (Thread/sleep 1000)
            (send- sender (.getBytes "")))))))
  (future
    (let [ctx (make-context 1)]
      (let [receiver (doto (make-socket ctx +upstream+)
                       (bind "tcp://*:5001"))]
        (recv receiver)
        (let [start (System/currentTimeMillis)]
          (dotimes [i 100]
            (recv receiver)
            (if (zero? (mod i 10))
              (print ":")
              (print ".")))
          (println "Total elapsed time:" (- (System/currentTimeMillis) start) "msec"))))))
