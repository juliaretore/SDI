# GNU Makefile
JAR= jar
JAVA= java
JC= javac
JFLAGS = #-Xlint


# JFLAGS = -g 
# .SUFFIXES: .java .class
# .java.class:
# 	$(JAVAC) $(JFLAGS) $*.java

# CLASSES = \
# 	MulticastReceiver.java\
# 	MulticastSender.java

# default: classes

# classes: $(CLASSES:.java=.class)

# clean:
# 	rm -f *.class 
# # GNU Makefile

# JC = /usr/local/jdk1.8.0_131/bin/javac
JFLAGS = #-Xlint
TARGET = ServerListener.class MultiThreadChatClient.class ClientThread.class ServerThread.class

all: $(TARGET)

ClientThread.class: ClientThread.java
	$(JC) $(JFLAGS) ClientThread.java 

ServerListener.class: ServerListener.java
	$(JC) $(JFLAGS) ServerListener.java 

MultiThreadChatClient.class: MultiThreadChatClient.java
	$(JC) $(JFLAGS) MultiThreadChatClient.java 

ServerThread.class: ServerThread.java
	$(JC) $(JFLAGS) ServerThread.java 

clean:
	rm -f *~ $(TARGET)