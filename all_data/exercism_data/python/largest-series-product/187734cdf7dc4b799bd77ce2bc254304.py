from functools import reduce
import operator

def largest_product(st, n):
    s = slices(st, n)
    return max([reduce(operator.mul, x, 1) for x in s])

def slices(st, n):
    if n > len(st):
        raise ValueError('index out of range')

    slice_list = []

    b,e = 0,n
    while e <= len(st):
        slice_list.append([int(s) for s in st[b:e]])
        b += 1
        e += 1

    return slice_list
