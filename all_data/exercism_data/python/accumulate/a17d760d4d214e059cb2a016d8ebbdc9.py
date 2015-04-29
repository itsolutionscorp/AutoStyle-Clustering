def accumulate(lst, f):
    res = list(lst)
    for i in range(len(res)):
        res[i] = f(res[i])
    return res
