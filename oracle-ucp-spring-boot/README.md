# oracle-ucp-spring-boot

sys���[�U�[��programacho���[�U�[�̏����ݒ���s���B

```shell
docker run --name oracle -d -p 1521:1521 -e ORACLE_PASSWORD=changeme gvenzl/oracle-xe:21.3.0-slim

sqlplus sys/changeme@//localhost:1521/XE as sysdba
```

```sql
CREATE USER programacho IDENTIFIED BY changeme;
GRANT CREATE SESSION TO programacho;
ALTER USER programacho QUOTA 100M ON users;
GRANT CREATE ANY SEQUENCE, SELECT ANY SEQUENCE TO programacho;
```

programacho���[�U�[�Ńe�[�u�����쐬����B

```shell
sqlplus programacho/changeme@//localhost:1521/XE
```

```sql
CREATE TABLE emp (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk PRIMARY KEY(id)
);
```
