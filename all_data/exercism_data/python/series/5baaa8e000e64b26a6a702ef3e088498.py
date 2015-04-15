def slices(digits, k):

    # Raise ValueError if length argument is larger than number of digits
    # or if length argument is zero
    if (len(digits) < k or k < 1):
        raise ValueError("Error: Length argument does not fit the series.")

    # Add list of k digits to series until no more k digit long slices left
    series = []
    i = 0   
    while i <= len(digits) - k:
        series.append([int(digit) for digit in digits[i:i+k]])
        i += 1
    return(series)
                      
