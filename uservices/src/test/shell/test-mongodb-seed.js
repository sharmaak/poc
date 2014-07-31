db.documents.remove()

db.documents.insert(
    { "name" : "Seed_01", "path" : "/tmp/s01", "_id" : ObjectId("53da752d823a2c69f78acf45") }
)
db.documents.insert(
    { "name" : "Seed_02", "path" : "/tmp/s02", "_id" : ObjectId("53da7552823a2c69f78acf46") }
)

db.documents.find()
    