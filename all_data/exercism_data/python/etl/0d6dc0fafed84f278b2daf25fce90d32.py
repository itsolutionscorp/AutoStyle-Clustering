def transform(old):
    result = {}
    for x in old.keys():
        for y in old[x]:
            result[y.lower()] = x

    return result
