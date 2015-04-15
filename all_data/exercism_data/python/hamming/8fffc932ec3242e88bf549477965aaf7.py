def distance(a,b):
    h = 0;
    for i in range(len(a)):
        if a[i] != b[i]:
            h = h + 1
    return h
