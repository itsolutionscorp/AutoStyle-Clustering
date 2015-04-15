def transform(data):
    d = dict()
    for k, v in data.items():
        for i in v:
            d[i.lower()] = k

    return d
