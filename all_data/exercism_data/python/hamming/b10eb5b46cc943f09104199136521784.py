def distance(a, b):
    if len(a) != len(b):
        return 0
    ham = 0
    for i in range(len(a)):
        if a[i] != b[i]:
            ham += 1
    return ham
