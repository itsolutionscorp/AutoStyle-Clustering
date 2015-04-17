def slices(digit_string, n):
    slice_arr = []
    if len(digit_string) < n or n < 1:
        raise ValueError('n not within bounds')
    else:
        for i in range(len(digit_string)-n+1):
            series = []
            for j in range(n):
                series.append(int(digit_string[i+j]))
            slice_arr.append(series)
    return slice_arr
