import functools

def slices(intstring, n):
    if n == 0:
        return []
    l = len(intstring)
    if not 0 < n <= l:
        raise ValueError
    return [[int(c) for c in intstring[i:i + n]] for i in range(l - n + 1)]

def _product(lst):
    return functools.reduce(lambda a, b: a*b, lst, 1)

def largest_product(intstring, n):
    return max((_product(slice) for slice in slices(intstring, n)), default=1)
