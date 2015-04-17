def slices(digits, length):
    if length > len(digits) or length <= 0:
        raise ValueError('The Pie is a Lie')
    values = []
    for i in range(len(digits) - length + 1):
        values.append([int(x) for x in digits[i:i + length]])
    return values
