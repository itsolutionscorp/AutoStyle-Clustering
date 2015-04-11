#series.py
#subsets are outlets for the soul
from operator import mul


def largest_product(series, size):
    if series == "":
        #weird identity property but okay if you want it that way
        #If you slice nothing into nothing you should get an error not a value
        return 1
    slice_list = slices(series, size)
    return max([reduce(mul, my_slice, 1) for my_slice in slice_list])


def slices(series, size):
    #from my exercism: series
    if (len(series)-size) < 0 or size == 0:
        raise ValueError('Slice is too large(or zero!?), just eat the whole thing why don\'t you?')
    return [map(int, list(series[i:size+i])) for i in range(0, (len(series)-size)+1)]
