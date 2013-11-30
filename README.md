## Install ZeroMQ and jzmq on MacOX

* install zeromq use `homebrew`

$ brew install zeromq

* install zeromq from source code

$ tar -zxvf zeromq-3.2.4.tar.gz

$ cd zeromq-3.2.4

$ ./configure CFLAGS="-m64" CXXFLAGS="-m64" LDFLAGS="-m64"

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

When I run it on MacOS, it always report `no jzmq in java.library.path`, as I have
set `:native-path "/usr/local/lib"`, at last I use some code to check the actually
`java.library.path`.

```
(println (. System getProperty "java.library.path"))
```
Then I found `/usr/local/lib` is not in `java.library.path`,then
I delete `:native-path "/usr/local/lib"` and add 
`:jvm-opts ["-Djava.library.path=/usr/local/lib"]`, then run it,
everything is ok.So it is egg ache,maybe it is a bug of `leiningen`,
I report a issue about it on github.

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
