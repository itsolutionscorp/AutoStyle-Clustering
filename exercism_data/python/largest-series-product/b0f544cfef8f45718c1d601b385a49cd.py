def slices(digit_string, n):
    slice_arr = []
    if len(digit_string) < n or n < 0:
        raise ValueError('n not within bounds')
    else:
        for i in range(len(digit_string)-n+1):
            series = []
            for j in range(n):
                series.append(int(digit_string[i+j]))
            slice_arr.append(series)
    return slice_arr

def largest_product(digit_string, n):
    slice_arr = slices(digit_string, n)
    max = 1
    for i in range(len(slice_arr)):
        product = 1
        for j in range(n):
            product = product * slice_arr[i][j]
        if product > max:
            max = product
    return max
