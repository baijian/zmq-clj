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

$ mvn install:install-file -DgroupId=org.zeromq -DartifactId=jzmq 
        -Dversion=2.2.2-SNAPSHOT -Dpackaging=jar -Dfile=/usr/local/share/java/zmq.jar 

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
