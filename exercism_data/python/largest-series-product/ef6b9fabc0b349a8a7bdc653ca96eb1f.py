# -*- coding: utf-8 -*-
import functools
import operator
prod = functools.partial(functools.reduce, operator.mul)

def slices(string_of_digits, length):
    difference = len(string_of_digits) - length
    if difference < 0 or length <= 0:
        raise ValueError
    else:
        return [[int(num) for num in string_of_digits[index: index + length]] for index in range(difference + 1)]

def largest_product(string_of_digits, length):
    if len(string_of_digits) == 0 and length == 0:
        return 1
    else:
        pick_list = slices(string_of_digits, length)
        prod_list = [prod(x) for x in pick_list]
        return max(prod_list)
