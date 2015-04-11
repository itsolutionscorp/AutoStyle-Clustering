from operator import mul

def largest_product(digits, n):
    if not digits:
        return 1
    return max(reduce(mul, s) for s in slices(digits, n))

def slices(digits, n):
    length = len(digits)
    if not 0 <= n <= length:
        raise ValueError('Invalid length {}'.format(n))
    digits = [int(d) for d in digits]
    return [digits[i:i+n] for i in range(length-n+1)]
