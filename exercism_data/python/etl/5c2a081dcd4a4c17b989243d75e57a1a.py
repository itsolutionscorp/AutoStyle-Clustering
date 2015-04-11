def transform(old):
    new = {}
    for key in old:
        for item in old[key]:
            new[item.lower()]=key
    return new
