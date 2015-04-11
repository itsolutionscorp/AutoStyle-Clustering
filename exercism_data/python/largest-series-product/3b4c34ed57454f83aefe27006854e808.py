# -*- coding: utf-8 -*-

def slices(digits, window):
    #possible input errors
    if len(digits)<window: raise ValueError("String length is less than slice")
    if not window: raise ValueError("Invalid slice size")
    if not digits.isdigit(): raise ValueError("String is not digit")

    digit_list = [int(x) for x in list(digits)]
    windows = (len(digits) - window) + 1
    return [digit_list[x:x+window] for x in range(windows)]

def list_product(digit_list):
    product = 1
    for x in digit_list:
        product *= x
    return product

def largest_product(digits, slice_size):
    if not digits or slice_size < 1:
        return 1
    groups = slices(digits, slice_size)
    output = []
    for g in groups:
        output.append(list_product(g))
    return max(output)

