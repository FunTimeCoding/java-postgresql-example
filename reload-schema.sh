#!/bin/sh -e

USER="example"
#psql -h 127.0.0.1 -U ${USER} -f schema.sql

# alternative, but less verbose:
DATABASE="example"
psql -h 127.0.0.1 -U ${USER} -d ${DATABASE} < schema.sql
