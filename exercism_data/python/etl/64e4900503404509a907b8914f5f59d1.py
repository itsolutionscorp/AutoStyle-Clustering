def transform(data):
    return dict((i.lower(), k) for (k, v) in data.items() for i in v)
