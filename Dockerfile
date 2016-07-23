FROM tomcat:7-jre7
MAINTAINER "andrija1987 <andrija.barbarosa@a-soft.si>"

ADD settings.xml /usr/local/tomcat/conf/
ADD tomcat-users.xml /usr/local/tomcat/conf/
