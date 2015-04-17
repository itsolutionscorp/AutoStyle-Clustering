def slices(digits, length):
    if length > len(digits) or length <= 0:
        raise ValueError("Series longer than string")
    return [map(int, list(digits[i:i + length]))
            for i in range(len(digits) - length + 1)]
