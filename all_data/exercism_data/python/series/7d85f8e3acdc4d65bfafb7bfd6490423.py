def slices(digits, n):

    if n > len(digits) or n == 0:
        raise ValueError("n must be an integer less than the length of digits and greater than 0")

    return [map(int, digits[i:i+n]) for i in range(len(digits) - n + 1)]
