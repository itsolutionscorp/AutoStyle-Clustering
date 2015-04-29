def transform(old):
    result = {}
    for(k, v) in old.iteritems():
        for word in v:
            result[word.lower()] = k
    return result
