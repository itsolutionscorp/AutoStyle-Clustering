def slices(digits, length):
    num_answers = len(digits) - length + 1
    # catch exceptions
    if num_answers <= 0:
        raise ValueError('Slice length is greater the number of digits.')
    # return a list of lists of all possible slices
    return [list(map(int, (digits[i:i + length]))) for i in range(num_answers)]


def largest_product(digits, length):
    all_series = slices(digits, length)
    # if no digits are passed it should return an answer of 1
    if all_series == [[]]:
        max_product = 1
    else:
        max_product = 0
        for series in all_series:
            # multiply together each element of the list
            series_product = reduce(lambda x, y: x * y, series)
            if series_product > max_product:
                max_product = series_product
    return max_product
