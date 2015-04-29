def slices(series, length):
    if not 0 < length <= len(series): raise ValueError
    digits = [ int(digit) for digit in series ]
    return [
        digits[i:i+length]
        for i in range(len(digits) - length + 1)
    ]
