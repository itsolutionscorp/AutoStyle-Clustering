def slices(digits, k):

    # Raise ValueError if length argument is larger than number of digits
    if (len(digits) < k):
        raise ValueError("Error: Length argument does not fit the series.")

    # Add list of k digits to series until no more k digit long slices left
    series = []
    i = 0   
    while i <= len(digits) - k:
        series.append([int(digit) for digit in digits[i:i+k]])
        i += 1
    return(series)
                      
def largest_product(digits, k):

    # Create slices
    slice_list = slices(digits, k)

    # Iterate over created slices, multiple together all numbers in each slice,
    # and return the largest
    max = 0
    for s in slice_list:
        temp = 1
        for digit in s:
            temp *= digit
        if temp > max:
            max = temp
    return(max)
