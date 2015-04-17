def slices(digits, length):
    if length > len(digits) or length <= 0:
        raise ValueError("Series longer than string")
    substrings = []
    for i in range(len(digits) - length + 1):
        substrings.append(map(int,list(digits[i:i+length])))
    return substrings
