def slices(string_of_digits, length):
    difference = len(string_of_digits) - length
    if difference < 0 or length <= 0:
        raise ValueError
    else:
        return [[int(num) for num in string_of_digits[index: index + length]] for index in range(difference + 1)]
