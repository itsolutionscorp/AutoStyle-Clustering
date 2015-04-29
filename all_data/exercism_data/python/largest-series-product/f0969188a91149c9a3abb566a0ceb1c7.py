def slices(digits, n):

    if n > len(digits) or n == 0:
        raise ValueError("n must be an integer less than the length of digits and greater than 0")

    return [map(int, digits[i:i+n]) for i in range(len(digits) - n + 1)]


def largest_product(series_string, n):

    if series_string == "":
        return 1
    else:
        slice_list = slices(series_string, n)
        products = [reduce(lambda x, y: x *y, l) for l in slice_list]

    return max(products)
