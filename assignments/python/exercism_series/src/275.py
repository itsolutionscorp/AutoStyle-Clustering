def slices(digits, n):
    length = len(digits)
    if not 0 < n <= length:
        raise ValueError('Invalid length {}'.format(n))
    digits = [int(d) for d in digits]
    return [digits[i:i+n] for i in range(length-n+1)]
