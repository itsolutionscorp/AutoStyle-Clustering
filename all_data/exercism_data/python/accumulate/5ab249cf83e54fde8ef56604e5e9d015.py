def accumulate(collection, operation):
    results = []
    for i in collection:
        results.append(operation(i))
    return results
