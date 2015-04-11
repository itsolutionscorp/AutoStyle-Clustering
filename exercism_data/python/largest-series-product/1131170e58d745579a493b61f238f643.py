#!/usr/bin/python3


def slices(digits_str, length):
    """
    Takes a string of digits and gives all the possible consecutive number
    series of length `length` in that string.
    """
    if length not in range(1, len(digits_str) + 1):
        raise ValueError('Given length not between [1, length of series]')
    range_ = range(0, len(digits_str) - length + 1)
    str_slices = [digits_str[idx:idx+length] for idx in range_]
    return [[int(char) for char in slice_] for slice_ in str_slices]


def mul(lst_of_nums):
    product = 1
    for num in lst_of_nums:
        product *= num
    return product


def largest_product(digits_str, length):
    if length == 0:
        return 1
    return max(mul(slice_) for slice_ in slices(digits_str, length))

if __name__ == "__main__":
    print(largest_product("01234", 2))
