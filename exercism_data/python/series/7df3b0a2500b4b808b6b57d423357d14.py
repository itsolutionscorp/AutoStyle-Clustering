def slices(digits, length):
    if length > len(digits) or length < 1:
        raise ValueError
    number_of_series = len(digits) - length + 1
    return [map(int,list(digits[i:i+length])) for i in range(number_of_series)]
