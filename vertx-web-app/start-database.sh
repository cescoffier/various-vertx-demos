#!/usr/bin/env bash
docker run --name some-postgres \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_USER=user \
  -e POSTGRES_DB=my_data \
  -d postgres:10.1-alpine