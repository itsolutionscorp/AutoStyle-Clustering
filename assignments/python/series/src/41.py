def slices(digits, n):
    """ Gives all possible consecutive number series of length 'n' in 'digits'
    """
    if n > len(digits) or n < 1:
        raise ValueError("Your inputs don't make sense.")
    return [[int(digit) for digit in series] for series in [list(digitstring) for digitstring in [digits[i:i + n] for i in range(len(digits) - n + 1)]]]
