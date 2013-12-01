(ns zmq-clj.sub
  (:use [org.zeromq.clojure :as zmq])
  (:gen-class))

(defn -main [& args]
  (let [fil (or (first args) "10001")
        ctx (make-context 1)]
    (with-open [sub (doto (make-socket ctx +sub+)
                      (connect "tcp://127.0.0.1:5000")
                      (.subscribe (.getBytes fil)))]
      (while true
        (let [msg (recv sub)]
          (println (str "Sub recv: " (String. msg))))))))
