(ns zmq-clj.core
  (:use org.zeromq.clojure)
  (:gen-class))

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello"))

(def *ctx* (make-context 0))

(future (let [s (make-socket *ctx* +upstream+)]
          (bind s "inproc://test")
          (loop [msg (recv s)]
            (println (str "Recv: " (String. msg)))
            (recur (recv s)))))

(doseq [i (range 0 5)]
  (future (let [s (make-socket *ctx* +downstream+)]
            (connect s "inproc://test")
            (loop [c 0]
              (send- s (.getBytes (str "hello " i " " c)))
              (Thread/sleep (rand 5000))
              (recur (inc c))))))
