def slices(seq, n):
    l = []
    if (n > len(seq)) or (n == 0):
        raise ValueError
    for i in range(n+1):
        x=[[int(s) for s in seq[i:i+n]] for i in range(len(seq))]
    for item in x:
        if len(item) == n:
            l.append(item)
    return l
