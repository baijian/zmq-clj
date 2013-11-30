## Install ZeroMQ and jzmq on MacOX

* install zeromq use `homebrew`

$ brew install zeromq

**or**

* install zeromq from source code

$ tar -zxvf zeromq-3.2.4.tar.gz

$ cd zeromq-3.2.4

$ ./configure CFLAGS="-m64" CXXFLAGS="-m64" LDFLAGS="-m64"

    you can use --prefix=$PROJECT/jzmq to specify the path you want
    to install maybe same folder with your leiningen project. And do not forget
    to set java.library.path=../jzmq. Do as this you do not hardcode the
    absolute path into your project.clj and you can get the specific version
    of jzmq your project requires.

$ make

$ sudo make install

* install following after install zeromq

$ brew install pkg-config

$ sudo ln -s /usr/local/share/aclocal/pkg.m4 /usr/share/aclocal/pkg.m4

$ git clone http://github.com/zeromq/jzmq.git

$ cd jzmq

$ ./autogen.sh

$ ./configure CFLAGS="-m64" CXXFLAGS="-m64" LDFLAGS="-m64"

$ make

$ sudo make install

* 可选

$ mvn install:install-file -DgroupId=org.zeromq -DartifactId=jzmq \
        -Dversion=2.2.2-SNAPSHOT -Dpackaging=jar -Dfile=/usr/local/share/java/zmq.jar 

* Attention

You should set java.library.path where you install jzmq into.
Set it with `:jvm-opts ["-Djava.library.path=/usr/local/lib"]` in
your project.clj.

You can use the following code to check your `java.library.path`.

```clojure
(println (. System getProperty "java.library.path"))
```

## Install ZeroMQ and jzmq on Linux

$ cd zeromq-3.2.4

$ ./configure

$ ./make

$ ./sudo make install

$ ls -all /usr/local/lib/libzmq.*

 $ cd jzmq

$ ./autogen.sh

$ ./configure

$ make 

$ sudo make install

$ ls -all /usr/local/lib/libjzmq*

$ ls -all /usr/local/share/java/*

## Run this demo

$ lein run
