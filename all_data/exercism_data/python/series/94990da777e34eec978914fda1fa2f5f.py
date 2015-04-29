__author__ = 'Cedric Zhuang'


def slices(s, length):
    slices_len = len(s) - length + 1
    if slices_len < 1 or length == 0:
        raise ValueError("error parameter")

    slice_arr = [None] * slices_len
    for i in range(slices_len):
        slice_arr[i] = map(int, s[i:i+length])
    return slice_arr
