from operator import mul

def slices(digits, N):
    if not N:
        return [[]]
    if N > len(digits):
        raise ValueError("N={} > len(digits)={}".format(N, len(digits)))

    digits = [int(d) for d in digits]
    return [digits[i:i+N] for i in range(len(digits)-N+1)]
    # note, this will return N+1 empty lists if N=0.
    # the original Series exercise called N=0 a ValueError, but i think
    # the empty list is a valid slice of 0 digits. though i can't quite decide
    # whether there should be N+1 empty lists or just 1 empty list...

def _product(values):
    return reduce(mul, values, 1)
    # not sure if i agree with an initializer of 1, but i guess if 0!=1, then sure.

def largest_product(digits, N):
    return max(_product(s) for s in slices(digits, N))
