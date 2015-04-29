'''exer etl'''

def transform(old_db):
    '''transform old_db format to new format'''

    new_db = {}
    for val in old_db:
        for elmnt in old_db[val]:
            new_db[elmnt.lower()] = val

    return new_db
