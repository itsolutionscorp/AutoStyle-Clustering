def slices(intstring, n):
    l = len(intstring)
    if not 0 < n <= l:
        raise ValueError
    return [[int(c) for c in intstring[i:i + n]] for i in range(l - n + 1)]
