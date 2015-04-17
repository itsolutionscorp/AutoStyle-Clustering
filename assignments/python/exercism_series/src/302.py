def slices(digits, n):
    slicesList = []
    digitsLen = len(digits)
    if (n < 1 or n > digitsLen):
        raise ValueError('slices argument %i out of bounds for digits %s' % (n, digits))
    for i in range(0, digitsLen - (n-1)): # Increment up range until end of slice hits end of digits
        sliceStr = digits[i:(i+n)]
        sliceStrList = list(sliceStr) # Separate out the member ints (they're strings still)
        sliceIntsList = [int(dig) for dig in sliceStrList] # Convert to ints
        slicesList.append(sliceIntsList)
    return slicesList
