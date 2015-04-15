def transform(old):
    new = {}
    for key in old:
        for value in old[key]:
            value = value.lower()
            if not value in new:
                new[value] = key
    return new
