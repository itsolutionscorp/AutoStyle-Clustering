def transform(old_data):
    result = {}
    for k, v in old_data.iteritems():
        for value in v:
            result[value.lower()] = k

    return result
