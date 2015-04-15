__author__ = 'nebur1989'


def slices(word, n):
    if (len(word) < n) or (n == 0):
        raise ValueError
    vect = []
    for c in word:
        vect.append(int(c))
    slc = []
    i = 0
    while (i + n) <= len(vect):
        slc.append(vect[i:i+n])
        i += 1
    return slc
