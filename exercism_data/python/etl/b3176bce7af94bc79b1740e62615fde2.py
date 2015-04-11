def transform(old):
    new = {}

    for k, v in old.items():
        for x in v:
            new[x.lower()] = k

    return new
