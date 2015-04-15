def transform(old_db):
    new_db = {}
    for point in old_db.keys():
        for letter in old_db[point]:
            new_db[letter.lower()] = point
    return new_db
