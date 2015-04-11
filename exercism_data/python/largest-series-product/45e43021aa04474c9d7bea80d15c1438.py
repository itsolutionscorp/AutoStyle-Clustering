def slices(number_string, size_of_slice):
    if size_of_slice > len(number_string):
        raise ValueError('Number of slices out of range!')

    # nested list comprehension fun
    # int() the character iteration of iterated slices of number_string
    return [[int(c) for c in number_string[x:x + size_of_slice]]
            for x in range(len(number_string) - size_of_slice + 1)]


def largest_product(series, slice_length):
    product_holder = 0

    for s in slices(series, slice_length):
        product_test = 1

        for n in range(len(s)):
            product_test *= s[n]

        if product_test > product_holder:
            product_holder = product_test

    return product_holder
