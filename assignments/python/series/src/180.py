def slices(digits, length):
    digits_len = len(digits)
    if digits_len < length or length < 1:
        raise ValueError()
    s = []
    for i in range(digits_len):
        slice = []
        for d in digits[i:i+length]:
            slice.append(int(d))
        if len(slice) == length:
            s.append(slice)
    return s
