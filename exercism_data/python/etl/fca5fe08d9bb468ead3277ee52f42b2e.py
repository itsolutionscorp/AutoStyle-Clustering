def transform(old):
    new = {}

    for k,v in old.items():
        for word in v:
            new[word.lower()] = k

    return new
