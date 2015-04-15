def slices(series, slicesize):
    nl = list(map(int, series))
    if slicesize > len(nl):
        raise ValueError("Slice size is bigger than the series size.")
    p = 0
    n = slicesize
    L = []
    while n <= len(nl):
        L.append(nl[p:p + slicesize])
        n += 1
        p += 1
    return L


def multiply(L):
    res = 1
    for i in L:
        res = i * res
    return res


def largest_product(series, n):
    x = 0
    if series:
        for i in slices(series, n):
            y = multiply(i)
            if y > x:
                x = y
        return x
    else:
        return 1
