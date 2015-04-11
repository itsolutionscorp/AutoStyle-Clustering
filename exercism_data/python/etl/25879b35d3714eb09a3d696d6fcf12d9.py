def transform(old_dict):
    new_dict = {}
    for key in old_dict:
        for item in old_dict[key]:
            new_dict[item.lower()] = key
    return new_dict
