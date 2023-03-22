# oracle-ucp-spring-boot
Setup local MySQL with docker if you want to check timeout related samples.
```sh
docker run --name mysql -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=changeme -e MYSQL_DATABASE=oracle_ucp_spring_boot mysql:8.0.32
```

Then you start this app with a mysql profile.
```sh
git clone https://github.com/hainet50b/oracle-ucp-gym.git
cd oracle-ucp-gym/oracle-ucp-spring-boot

./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

After you try samples, clean up resources.
```sh
docker rm $(docker stop mysql)
```
