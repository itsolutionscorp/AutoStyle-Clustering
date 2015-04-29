def transform(old):
    new = {}
    for k, v in old.iteritems():
        for s in v:
            new[s.lower()] = k
    return new
