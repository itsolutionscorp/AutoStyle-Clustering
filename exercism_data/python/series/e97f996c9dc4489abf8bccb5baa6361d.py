def slices(number_to_slice, slice_size):
    number_to_slice = [int(digit) for digit in list(number_to_slice)]
    digits_length = len(number_to_slice)
    if digits_length < slice_size or slice_size < 1:
        raise ValueError
    return [number_to_slice[i:i + slice_size] for i in range(0, len(number_to_slice) - slice_size + 1)]
