def largest_product(digits, length):
    if length <= 0:
        return 1
    slices_of_input = slices(digits, length)
    largest = 1
    for each_slice in slices_of_input:
        product = 1
        for each_digit in each_slice:
            product *= each_digit
        if product > largest:
            largest = product
    return largest


def slices(digits, length):
    digits_length = len(digits)
    if digits_length < length:
        raise ValueError('Length of slices is greater than the string itself.')
    elif length <= 0:
        raise ValueError('Length needs to be positive.')

    return_list = []
    for start in range(digits_length - length + 1):
        current_slice = digits[start:start+length]
        return_list.append([int(n) for n in current_slice])
    return return_list
