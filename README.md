START POSTGRES:
-----------------------
```
docker run -it --rm -e POSTGRES_PASSWORD=postgres --name postgres -p 5432:5432 postgres -d postgres
```