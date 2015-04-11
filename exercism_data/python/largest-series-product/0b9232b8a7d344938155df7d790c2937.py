from itertools import tee, izip, islice

def slices(digits, chunksize):
    if chunksize > len(digits):
        raise ValueError('Invalid chunksize')

    t = tee(digits, chunksize)

    for i, j in enumerate(t):
        next(islice(j, i, i), None)

    return [map(lambda c: int(c), k) for k in izip(*t)]

def largest_product(digits, chunksize):
    if not digits:
        return 1

    return max(reduce(lambda n1, n2: n1 * n2, p) for p in slices(digits, chunksize))
