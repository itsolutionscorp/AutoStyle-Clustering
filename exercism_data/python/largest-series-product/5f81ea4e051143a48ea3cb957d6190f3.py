import re

def largest_product(digits_string, series_size):
    validate_args(digits_string,series_size)

    # Get the list of slices
    # Then for each slice calculate its product
    # Finally, choose the largest value
    return max([reduce(lambda x,y: x * y, slice) for slice in slices(digits_string, series_size)])

def slices(digits_string, slice_size):
    validate_args(digits_string,slice_size)
    digits_length = len(digits_string)

    if len(digits_string) == 0:
        return [[1]]

    result = []
    start = 0

    numbers_list = [int(digit) for digit in digits_string ]

    while start + slice_size <= digits_length:
        result.append(numbers_list[start:slice_size+start])
        start += 1

    return result

def validate_args(digits_string,size):
    if isinstance(digits_string,basestring) and isinstance(size,(int,long)) and re.match('[0-9]*',digits_string):
        if size > len(digits_string):
            raise ValueError('Invalid values. The size of the slice cannot be greater than the length of the string.')
    else:
        raise ValueError('Invalid values. Please provide a string of digits and a number.')
