def slices(digit_string, n):
    m = 0
    series = []
    digit_length = len(digit_string)
    digit_string_list = [int(x) for x in list(digit_string)]
    if n == 0 or n > digit_length:
        raise ValueError
    while n < digit_length + 1:
        for i in range(digit_length - n + 1):
            series.append(digit_string_list[m:n])
            m, n = m + 1, n + 1
    return series
