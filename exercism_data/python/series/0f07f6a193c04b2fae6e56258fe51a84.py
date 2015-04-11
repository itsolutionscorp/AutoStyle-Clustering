def slices(digits, slice_length):
    if not len(digits) >= slice_length > 0:
        raise ValueError("The slice length {0} is greater than the length of the digit string {1}.".format(slice_length, len(digits)))
    reps = len(digits) - slice_length + 1
    slice_list = []
    for i in range(reps):
        slice_list.append([int(x) for x in digits[i:slice_length + i]])
    return slice_list
