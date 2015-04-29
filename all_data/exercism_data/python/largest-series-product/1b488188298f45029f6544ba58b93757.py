def largest_product(st, ln):
    mx = 0
    lt = slices(st, ln)
    for l in lt:
        m = 1
        for x in l:
            m = m * x
        if mx < m:
            mx = m
    return mx


def slices(st, ln):
    if len(st) < ln:
        raise ValueError
    ret = []
    for i in range(len(st)-ln+1):
        tmp = []
        for x in list(st[i:i+ln]):
            tmp.append(int(x))
        ret.append(tmp)
    return ret
