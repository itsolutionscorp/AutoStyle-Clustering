def transform(old_dict):
    new_dict = {}
    for key, value in old_dict.items():
        for element in value:
            new_dict[element.lower()] = key
    return new_dict
