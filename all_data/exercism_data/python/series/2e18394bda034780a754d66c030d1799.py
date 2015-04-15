def slices(string, n):
    res = []
    if n > len(string) or n == 0:
        raise ValueError("Incompatible string and slice sizes")
    else:
        for i in range(len(string) - n+1):
            res.append([int(x) for x in string[i:i+n]])
    return res
