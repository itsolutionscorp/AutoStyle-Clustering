def slices(number_string, size_of_slice):
    if size_of_slice > len(number_string) or not size_of_slice:
        raise ValueError('Number of slices out of range!')

    # nested list comprehension fun
    # int() the character iteration of iterated slices of number_string
    return [[int(c) for c in number_string[x:x + size_of_slice]]
                   for x in range(len(number_string) - size_of_slice + 1)]