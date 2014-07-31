#!/bin/bash

# Prerequisite: 
# ============
# Seed the following into mongodb collection (mongodb params are described in the yaml file in 'resources/conf'
#    
#    db.documents.insert(
#        { "name" : "Seed_01", "path" : "/tmp/s01", "_id" : ObjectId("53da752d823a2c69f78acf45") },
#    )
#    
#    db.documents.insert(
#        { "name" : "Seed_02", "path" : "/tmp/s02", "_id" : ObjectId("53da7552823a2c69f78acf46") }
#    )

echo "****** GET ********"
curl -i -X GET http://localhost:8080/documents/53da752d823a2c69f78acf45
echo 
echo "======================"
echo 

echo "****** POST ********"
curl -i -X POST -H "Content-Type: application/json" -d '{"name":"document_01","path":"/tmp/01"}' http://localhost:8080/documents
echo 
echo "======================"
echo 

echo "****** PUT ********"
curl -i -X PUT -H "Content-Type: application/json" -d '{"id": "53da7552823a2c69f78acf46", "name":"document_99","path":"/tmp/99"}' http://localhost:8080/documents
echo 
echo "======================"
echo 

echo "****** GET ********"
curl -i -X GET http://localhost:8080/documents/53da7552823a2c69f78acf46
echo 
echo "======================"
echo 

echo "****** DELETE ********"
curl -i -X DELETE http://localhost:8080/documents/53da7552823a2c69f78acf46
echo 
echo "======================"
echo 

echo "****** SEARCH ********"
curl -i -X GET http://localhost:8080/documents/search/%7B%7D/4
echo 
echo "======================"
echo 

