def transform(old):
    result = {}
    for key, values in old.iteritems():
        for value in values:
            result[value.lower()] = key
    return result
