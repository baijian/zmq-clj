(defproject zmq-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;This sets the 'java.library.path' property
  ;so java can find the ZeroMQ dylib
  :native-path "/usr/local/lib"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.zeromq/jzmq "2.2.2-SNAPSHOT"]
                 [org.clojars.mikejs/clojure-zmq "2.0.7-SNAPSHOT"]]
  :main ^:skip-aot zmq-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
