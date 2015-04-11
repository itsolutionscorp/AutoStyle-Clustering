def hamming(a, b):
    a=list(a)
    b=list(b)
    diff = max(len(a) - len(b))
    if len(a) > len(b):
        while diff >0:
            b.append('x')
            diff-=1
        return sum(1 for i, j in zip(a, b) if i != j)
    elif len(a) < len(b):
        while diff >0:
            a.append('x')
            diff-=1
        return sum(1 for i, j in zip(a, b) if i != j)
    else:
        return sum(1 for i, j in zip(a, b) if i != j)
