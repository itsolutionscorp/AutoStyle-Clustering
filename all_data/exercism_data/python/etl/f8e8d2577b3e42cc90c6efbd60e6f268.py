def transform(old):
    new = {}
    for k, v in old.iteritems():
        for vi in v:
            new[vi.lower()] = k
    return new
