def transform(old):
    new = {}
    for k in old:
        value_array = old[k]
        for v in value_array:
            v = v.lower()
            new[v] = k            
    return new
