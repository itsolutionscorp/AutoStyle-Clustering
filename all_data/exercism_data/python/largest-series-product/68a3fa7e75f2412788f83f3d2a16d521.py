__author__ = 'jeffmarkey'
import sys


def largest_product(full_number, slice_size):
    options = slices(full_number, slice_size)

    if(full_number==''):
        return 1

    max = -sys.maxint - 1
    current = -sys.maxint - 1
    for line in options:
        for number in line:
            if(current!= -sys.maxint - 1):
                current = current * number
            else:
                current = number
        if(current > max):
            max = current
        current = -sys.maxint - 1

    return max

def slices(full_number, slice_size):
    full_number = list(full_number)
    full_list = []

    if (slice_size > len(full_number)):
        raise ValueError

    for i in xrange(0, len(full_number)-slice_size+1):
        full_list.append(full_number[i:i+slice_size])

    new_full_list = []
    for slice in full_list:
        current_list = []
        for short_list in slice:
            current_list.append(int(short_list))
        new_full_list.append(current_list)

    return new_full_list
