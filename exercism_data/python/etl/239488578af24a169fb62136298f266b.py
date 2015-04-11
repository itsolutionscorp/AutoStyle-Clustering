def transform(old):
    new = {}
    for key in old:
        for value in old[key]:
            new[value.lower()] = key
    return new
