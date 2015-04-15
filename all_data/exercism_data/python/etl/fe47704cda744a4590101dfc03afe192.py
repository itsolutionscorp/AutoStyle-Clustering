def transform_v1(old):
    new = dict()
    for key in old:
        for value in old[key]:
            value = value.lower()
            new[value] = key
    return new
