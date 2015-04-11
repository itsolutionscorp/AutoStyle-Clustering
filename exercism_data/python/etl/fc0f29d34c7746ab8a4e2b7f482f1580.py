def transform(old):
    new = dict()
    for score,values in old.items():
        for key in values:
            new[key.lower()] = score
    return new
