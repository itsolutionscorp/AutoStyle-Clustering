from itertools import islice
def slices(string, n):
    length = len(string)
    if not 0 < n <= length:
        raise ValueError('Invalid length {}'.format(n))
    digits = [int(d) for d in string]
    return [digits[i:i+n] for i in range(length-n+1)]
