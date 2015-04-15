def transform(old):
    new = {}
    for key in old:
        if type(old[key]) is str:
            old[key] = list(old[key])
        for l in old[key]:
            new[l.lower()] = key
    return new
