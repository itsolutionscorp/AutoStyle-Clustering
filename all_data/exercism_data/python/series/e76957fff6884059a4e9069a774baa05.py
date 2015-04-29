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
        raise ValueError

    return [[int(x) for x in digits[i:i+n]] for i in range(digits_len) if i+n <= digits_len ]
