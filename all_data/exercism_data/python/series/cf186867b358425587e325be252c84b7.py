def slices(digits, length):
    digits_length = len(digits)
    if digits_length < length:
        raise ValueError('Length of slices is greater than the string itself.')
    elif length <= 0:
        raise ValueError('Length needs to be positive.')

    return_list = list()
    for start in range(digits_length - length + 1):
        current_slice = digits[start:start+length]
        return_list.append(map(int, list(current_slice)))
    return return_list
