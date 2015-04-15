def slices(input_str, length):
    if length > len(input_str) or length == 0:
        raise ValueError('Slice Length Invalid')
    slices = []
    for start_index in range(0, len(input_str) - length + 1):
        slices.append([int(x) for x in input_str[start_index : start_index + length]])
    return slices

def largest_product(input_str, length):
    if length == 0:
        return 1
    slice_list = slices(input_str, length)
    return max([reduce(lambda x,y: x*y, L) for L in slice_list])
