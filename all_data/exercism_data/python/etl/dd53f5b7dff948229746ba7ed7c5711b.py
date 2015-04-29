def transform(old):
    res = {}
    for count in old.keys():
        for val in old[count]:
            res[val.lower()] = count
    return res
