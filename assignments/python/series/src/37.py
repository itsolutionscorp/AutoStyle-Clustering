def slices(digits, length):
    if length < 1 or length > len(digits):
        raise ValueError
    slices = list()
    for i in range(0, len(digits) - length + 1):
        slices.append([int(i) for i in digits[i:i+length]])
    return slices
