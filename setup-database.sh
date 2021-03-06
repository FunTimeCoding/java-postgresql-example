#!/bin/sh -e

ADMIN_USER="postgres"
USER="example"
PASSWORD="example"
DATABASE="example"
sudo -u ${ADMIN_USER} psql -U ${ADMIN_USER} -c "
CREATE USER ${USER}
WITH PASSWORD '${PASSWORD}';
CREATE DATABASE ${DATABASE}
WITH OWNER ${USER}
TEMPLATE template0
ENCODING 'SQL_ASCII'
TABLESPACE pg_default
LC_COLLATE 'C'
LC_CTYPE 'C'
CONNECTION LIMIT -1"
