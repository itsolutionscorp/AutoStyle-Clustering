def slices(digits, length):
    num_answers = len(digits) - length + 1
    if num_answers <= 0:
        raise ValueError('Slice length is greater the number of digits.')
    if length <= 0:
        raise ValueError('Slice length must be at least 1.')
    return [list(map(int, (digits[i:i + length]))) for i in range(num_answers)]
