#!/bin/bash
set -e

PIAWARE_HOST=192.168.4.233 \
RESTCLIENT_URIBASE=http://localhost:8001 \
docker compose up --build