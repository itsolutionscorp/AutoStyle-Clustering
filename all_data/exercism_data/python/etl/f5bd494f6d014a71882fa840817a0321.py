def transform(old):
    transformed = {}
    for k,v in old.iteritems():
        for individual in v:
            transformed[individual.lower()] = k
    return transformed
