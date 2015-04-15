def transform(arg):
    ret = {}
    temp = {}
    for x in arg.keys():
        for y in arg[x]:
            temp[y.lower()] = x
    for k,v in sorted(temp.items()):
        ret[k] = v
    return ret
