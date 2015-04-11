from operator import mul
from functools import reduce

def largest_product(input_str, slice_number):
    # For some unknown reason, max() doesn't pick out
    # the max list
    # temp_list = max(slices(input_str, slice_number))
    # print (temp_list)

    max_score = 1

    for numbers_list in slices(input_str, slice_number):
        if 0 not in numbers_list:
            # using mul without lambda runs faster
            # http://stackoverflow.com/questions/2104782/returning-the-product-of-a-list
            total = reduce(mul, numbers_list)
            if max_score <= total:
                max_score = total

    return max_score

def slices(input_str, slice_number):
    if len(input_str) < slice_number:
        raise ValueError(str(slice_number) + " is not a valid length argument.")

    input_str = list(map(int, input_str))
    slice_list = [ input_str[i:slice_number + i] for i in range(len(input_str)) if len(input_str[i:]) >= slice_number ]

    return slice_list
