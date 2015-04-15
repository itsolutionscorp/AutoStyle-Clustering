__author__ = 'nebur1989'


def slices(word, n):
    if len(word) < n:
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


def largest_product(word, n):
    slc = slices(word, n)
    largest = 1
    for slice in slc:
        prod = 1
        for element in slice:
            prod = prod * element
        if prod > largest:
            largest = prod
    return largest
