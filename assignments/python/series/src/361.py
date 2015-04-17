def slices(number, slice_length):
    if slice_length <= len(number) and slice_length != 0:
        return [[int(number[i + each]) for each in range(0, slice_length)] for i, x in enumerate(number) if i + slice_length <= len(number)]
    else:
        raise ValueError
