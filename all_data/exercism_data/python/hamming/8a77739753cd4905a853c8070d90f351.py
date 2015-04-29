__author__ = 'cameron'

def hamming(x, y):

    if len(x) > len(y):
        y = y + ('0' * (len(x) -len(y)))
    if len(x) < len(y):
        x = x + ('0' * (len(y) - len(x)))

    i = 0
    mut = 0

    for a in x:
        if a != y[i]:
            mut += 1
        i += 1

    return mut
