def slices(digits, length):
    slicing = []
    digits_holder = digits
    if length == 0 or length > len(digits):
        raise ValueError
    else:
        while len(digits_holder) >= length:
            slicing.append([int(digits_holder[x]) for x in xrange(length)])
            digits_holder = digits_holder[1:]
    return slicing
