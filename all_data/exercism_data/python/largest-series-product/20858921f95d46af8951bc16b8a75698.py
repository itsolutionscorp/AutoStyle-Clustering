import re

# Calculate the largest product for a series of consecutive digits of length n.
def largest_product(digits_string, series_size):

    # Validate the inputs
    validate_args(digits_string,series_size)

    # Get the list of slices
    # Then for each slice calculate its product
    # Finally, choose the largest value
    return max([reduce(lambda x,y: x * y, slice) for slice in slices(digits_string, series_size)])

# Generate a list of series of consecutive digits of length n.
def slices(digits_string, slice_size):

    # Validate the inputs
    validate_args(digits_string,slice_size)

    # Get the size of string of digits
    digits_length = len(digits_string)

    # Return a list of a single slice when receiving an empty string of digits
    if len(digits_string) == 0:
        return [[1]]

    result = []
    start = 0

    # Convert the digits from strings to ints, and store them on a list
    numbers_list = [int(digit) for digit in digits_string ]

    # Add slices of size n for as long as we don't reach the end of the list
    while start + slice_size <= digits_length:
        
        result.append(numbers_list[start:slice_size+start])

        # Advance one position at a time (i.e. there is overlapping)
        start += 1

    return result

# Validate arguments' types and values
def validate_args(digits_string,size):

    # Validate types
    if isinstance(digits_string,basestring) and isinstance(size,(int,long)) and re.match('[0-9]*',digits_string):

        # Check that the size of the slice is not greater than the string
        if size > len(digits_string):
            raise ValueError('Invalid values. The size of the slice cannot be greater than the length of the string.')
    else:
        raise ValueError('Invalid values. Please provide a string of digits and a number.')
