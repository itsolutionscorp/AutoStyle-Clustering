def transform(old):
    new_dict = {}
    for key, value in old.iteritems():
        for item in value:
            new_dict[item.lower()] = key
    return new_dict
