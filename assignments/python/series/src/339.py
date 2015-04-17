def slices(number_to_slice, slice_size):
    number_to_slice = list(map(int, number_to_slice))
    digits_length = len(number_to_slice)
    if digits_length < slice_size or slice_size < 1:
        raise ValueError('Invalid Slice Size')
    return [number_to_slice[i:i + slice_size] for i in range(0, len(number_to_slice) - slice_size + 1)]
