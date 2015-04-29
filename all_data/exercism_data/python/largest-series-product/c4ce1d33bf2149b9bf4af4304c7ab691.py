from functools import reduce

def slices(num_string, digits):
    string_len = len(num_string)
    slice_list = []
    if digits == 0 or digits > len(num_string):
        raise ValueError("%i is not a valid slice size for string %s", (digits, num_string))
    else:
        for position in range(0,string_len - digits + 1):
            slice_str = num_string[position:position+digits]
            sliced = []
            for num in slice_str:
                sliced.append(int(num))
            slice_list.append(sliced)
    return slice_list

def largest_product(num_string, digits):
    if digits == 0:
        return 1
    slice_list = slices(num_string, digits)
    maximum = 0
    for item in slice_list:
        product = reduce(lambda x, y: x*y, item)
        if product > maximum:
            maximum = product
    return maximum
