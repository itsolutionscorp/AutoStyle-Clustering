def largest_product(digits, slice_size):
    products = [reduce(lambda x, y: x * y, array) for array in
                slices(digits, slice_size)]
    products.append(1)
    return max(products)


def slices(digits, slice_size):
    if slice_size > len(digits):
        raise ValueError('Slice too long.')
    return [make_int_array(digits[i:i + slice_size]) for i in
            range(len(digits) - 1)]


def make_int_array(digits):
    return [int(x) for x in digits]
