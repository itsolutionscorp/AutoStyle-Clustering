""" Transforms old key values to new format """

def transform(old):
    result = {}
    for point, values in old.iteritems():
        for value in values:
            result[value.lower()] = point
    return result
