def distance(a,b):
    h = 0;
    for ai, bi in zip(a,b):
        if ai != bi:
            h = h + 1
    return h
