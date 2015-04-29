def slices(digits, length):
    if(length == 0 or length > len(digits)):
        raise ValueError('Invalid slice length')
    digits = [int(d) for d in digits]
    return [list(digits[i:i + length]) for i in range(len(digits) - length + 1)]
