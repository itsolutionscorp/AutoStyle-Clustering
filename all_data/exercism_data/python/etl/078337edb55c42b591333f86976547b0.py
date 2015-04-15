def transform(old):
    new = {}
    for point, values in old.items():
        for item in values:
            new[item.lower()] = point
    return new
