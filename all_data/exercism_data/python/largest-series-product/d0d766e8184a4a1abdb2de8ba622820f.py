from functools import reduce
from operator import mul

def largest_product(digits, n):
    if n > len(digits):
        raise ValueError("n must be greater than len(digits)")
    if n == 0:
        return 1
    # the above check can be replaced by return n and max(...) or 1
    return max(reduce(mul, sl) for sl in slices(digits, n))

def slices(digits, n):
    if n > len(digits):
        raise ValueError("n must be greater than len(digits)")
    return [[int(d) for d in digits[i:i+n]] for i in range(0, len(digits) - n + 1)]
