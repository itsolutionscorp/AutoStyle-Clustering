def accumulate(collection, function):
    applied_collection = []
    for item in collection:
        applied_collection.append(function(item))
    return applied_collection
