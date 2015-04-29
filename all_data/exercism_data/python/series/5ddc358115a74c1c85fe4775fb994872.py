def slices(st, num):
    ret = []
    if len(st) < num or num < 1:
        raise ValueError

    for i in range(num, len(st)+1):
        tmp = []
        for j in range(i-num, i):
            tmp.append(int(st[j]))
        ret.append(tmp)
    return ret
