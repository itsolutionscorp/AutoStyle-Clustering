def slices(x, n):
    x = [int(i) for i in x]
    out = [x[a:a+n] for a in range(len(x)-n+1)]
    if not out or not n:
        raise ValueError
    else:
        return out
