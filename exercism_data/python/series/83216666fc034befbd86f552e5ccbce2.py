def slices(number, slice_length):

    if (not isinstance(number, str)):
        number = repr(number)

    if (not number.isnumeric()):
        raise ValueError("Parameter 'number' is not numeric")
    if (slice_length == 0):
        raise ValueError("Parameter 'slice_length' cannot be zero")
    if (slice_length < 0):
        raise ValueError("Parameter 'slice_length' cannot be negative")
    if (slice_length > len(number)):
        raise ValueError("Parameter 'slice_length' exceeds the number's length")

    number_of_slices = len(number) - slice_length + 1

    return [
        [ int(n) for n in number[offset:slice_length + offset] ]
        for offset in range(0, number_of_slices)
    ]
