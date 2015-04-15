__author__ = 'Flavio Miranda'


def largest_product(arr, large):
    if large > len(arr):
        raise ValueError("Error")

    return max([prod(i) for i in slices(arr, large)])


def prod(lst):
    prd = 1
    for i in lst:
        prd *= i
    return prd


def substr(sub, i, c):
    return sub[i:i + c]


def slices(toslice, large):
    ln = len(toslice)
    if large > ln:
        raise ValueError("Error")

    slice = []
    for i in range(ln - large + 1):
        tmp = []
        for j in range(large):
            tmp.append(int(toslice[i + j]))
        slice.append(tmp)

    return slice
