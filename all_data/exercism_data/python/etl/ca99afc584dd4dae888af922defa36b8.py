def transform(old):
    new = {}
    for key in old:
        for word in old[key]:
            new[word.lower()] = key
    return new
