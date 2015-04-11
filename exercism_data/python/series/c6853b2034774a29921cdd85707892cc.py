def slices(digits, n):
    if not n or n > len(digits):
        raise ValueError(
            'Unable to create slices of {} length'.format(n)
        )
    digits = [int(digit) for digit in list(digits)]
    series = []
    for i, digit in enumerate(digits):
        s = digits[i:i+n]
        if len(s) == n:
            series.append(s)
    return series
