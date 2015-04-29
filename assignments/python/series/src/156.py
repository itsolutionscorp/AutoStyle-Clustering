def slices(digits, k):
    if (len(digits) < k or k < 1):
        raise ValueError("Error: Length argument does not fit the series.")
    series = []
    i = 0   
    while i <= len(digits) - k:
        series.append([int(digit) for digit in digits[i:i+k]])
        i += 1
    return(series)
