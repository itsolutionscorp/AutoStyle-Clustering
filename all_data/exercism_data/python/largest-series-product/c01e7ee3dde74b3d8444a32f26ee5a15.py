__author__ = 'banarasitippa'

def slices(digits,n):
    '''
    program will take a string of digits and give you all the possible consecutive
    number series of length `n` in that string.
    :param digits: String
    :param n: int
    :return:
    '''
    digits_len = len(digits)
    if not (digits.isdigit() and digits_len >= n and n):
        raise ValueError("n greater than number of digits")

    return [[int(x) for x in digits[i:i+n]] for i in range(digits_len-n+1) ]

def mul(value1, value2):
    return value1*value2

def largest_product(digits,n):
    '''
    program for given a string of digits, wil calculate the
    largest product for a series of consecutive digits of length n.
    :param digits: string
    :param n: int
    :return:
    '''
    if n == 0:
        return 1
    if len(digits) < n:
        raise ValueError("n greater than number of digits")

    # take every slice find the product and pick the maximum out of it
    return max(reduce(mul, (digit for digit in slice if digit)) for slice in slices(digits, n))
