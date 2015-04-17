def slices(digits_string, slices_size):
    if (slices_size > len(digits_string)):
        raise ValueError('Slices cannot be longer than the string.')
    elif (slices_size == 0):
        raise ValueError('Slices cannot have zero size.')
    digits_sequence = map(lambda x: int(x), list(digits_string))
    output_slices = []
    for i in range(len(digits_sequence) - slices_size + 1):
        output_slices.append(digits_sequence[i:(i + slices_size)])
    return output_slices
