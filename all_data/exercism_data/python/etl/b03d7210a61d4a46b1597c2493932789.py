def transform(old_dict):
    new_dict = {}
    for key, value in old_dict.iteritems():
        for item in value:
            new_dict[item.lower()] = key
    return new_dict
