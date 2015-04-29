def largest_product(series, slice_size):
    product = lambda x,y: x*y
    sliced_series = slices(series, slice_size)
    return max([reduce(product, sliced_series[i]) for i in range(len(sliced_series))])

def slices(series, slice):
    slice_too_large = slice > len(series)
    slice_too_small = slice < 1

    if slice_too_small or slice_too_large:
        raise ValueError
    else:
        slices = []
        series_list = [int(number) for number in series]
            
        while len(series_list) >= slice:
            slices.append([series_list[number] for number in range(slice)])
            series_list = series_list[1:]
    return slices

"""
# Largest Series Product

Write a program that, when given a string of digits, can calculate the largest product for a series of consecutive digits of length n.

For example, for the input `'0123456789'`, the largest product for a
series of 3 digits is 504 (7 * 8 * 9), and the largest product for a
series of 5 digits is 15120 (5 * 6 * 7 * 8 * 9).

For the input `'73167176531330624919225119674426574742355349194934'`,
the largest product for a series of 6 digits is 23520.
"""
