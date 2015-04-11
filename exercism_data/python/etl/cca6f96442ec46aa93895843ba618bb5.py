def transform(old):
    new = {}
    for value in old:
        for item in old[value]:
            new[item.lower()] = value
    return new
