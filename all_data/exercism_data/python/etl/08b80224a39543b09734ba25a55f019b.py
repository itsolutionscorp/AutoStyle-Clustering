def transform(data):
    ret = {}
    for key, items in data.items():
        for item in items:
            ret[item.lower()] = key
    return ret
