def transform(old_data):
    new_data = {}
    for key, value in old_data.items():
        for item in value:
            new_data[item.lower()] = key
    return new_data
