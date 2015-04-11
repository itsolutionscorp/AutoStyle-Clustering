def hamming(a, b):
    al=len(a)
    bl=len(b)
    a=list(a)
    b=list(b)
    if len(a) > len(b):
        diff = al - bl
        while diff >0:
            b.append('x')
            diff-=1
        return sum(1 for i, j in zip(a, b) if i != j)
    elif len(a) < len(b):
        diff = bl - al
        while diff >0:
            a.append('x')
            diff-=1
        return sum(1 for i, j in zip(a, b) if i != j)
    else:
        return sum(1 for i, j in zip(a, b) if i != j)
