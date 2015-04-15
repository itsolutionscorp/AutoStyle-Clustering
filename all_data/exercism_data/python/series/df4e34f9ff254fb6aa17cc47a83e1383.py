def slices(digits, length):
    if length < 1:
        raise ValueError('Length to slice must be greater than 0')
    if length > len(digits):
        raise ValueError('Length to slice must not be greater than the length of the string')
    return [[int(digit) for digit in digits[start:start+length]] for start in range(0, len(digits) - length + 1)]
