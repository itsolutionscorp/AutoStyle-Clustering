def largest_product(series, series_len):
    series = slices(series, series_len)
    largest = 0
    for slist in series:#slist == [7,8,9]
        x = product(slist)
        if x > largest:
            largest = x
    return largest

def product(slist):
    result = 1
    for n in slist:
        result*= n
    return result

def slices(series, series_len):
    result = []
    index = 0
    if series_len > len(series) or series_len < 1:
        raise (ValueError)
    while (index + series_len) <= len(series):
        nlist = []
        for i in range(index, index+series_len):
            nlist.append(int(series[i]))
        index += 1
        result.append(nlist)
    return result

"""Tests for the largest-series-product exercise

Implementation note:
In case of invalid inputs to the 'slices' or 'largest_product' functions
your program should raise a ValueError with a meaningful error message.

Feel free to reuse your code for the series exercise!
"""
"""
Write a program that, when given a string of digits, can calculate the largest
product for a series of consecutive digits of length n.

For example, for the input `'0123456789'`, the largest product for a
series of 3 digits is 504 (7 * 8 * 9), and the largest product for a
series of 5 digits is 15120 (5 * 6 * 7 * 8 * 9).

For the input `'73167176531330624919225119674426574742355349194934'`,
the largest product for a series of 6 digits is 23520.
"""
