#!/usr/bin/env python

def slices(array, chunk):
    if chunk > len(array) or chunk <= 0:
        raise ValueError

    ret_array = []
    for i in range(len(array) + 1 - chunk):
        tmp_array = []
        for j in range(chunk):
            tmp_array.append(int(array[i+j]))

        ret_array.append(tmp_array)
    
    return ret_array

def slice_product(array):
    product = 1
    for i in array:
        product = product * i
    return product

def largest_product(num, slice_size):
    try:
        num_in_slices = slices(num, slice_size)
    except ValueError:
        if slice_size == 0 or not num:
            return 1
        else:
            raise ValueError
    product = []
    for i in num_in_slices:
        product.append(slice_product(i))
    product.sort()
    return product.pop()
