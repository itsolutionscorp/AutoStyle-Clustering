def transform(what):
    dc = dict()
    for i,j in what.items():
        for k in j:
            dc[k.lower()] = i
    return(dc)
