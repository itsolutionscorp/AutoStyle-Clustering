from operator import mul

def slices(digits, n):

    l = len(digits)
    if n > l:
        raise ValueError('The requested length is longer than the base string')

    if n < 1:
        return [[]]

    dlist =  [int(d) for d in digits]

    s = []
    for i in range(l-n+1):
        s.append(dlist[i:i+n])

    return s

def largest_product(digits, n):
    
    all_slices = slices(digits, n)
    return max([reduce(mul, s, 1) for s in all_slices])
