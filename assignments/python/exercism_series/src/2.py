def slices(digits, size):
    digitslength = len(digits)
    if size == 0 or size > digitslength:
        raise ValueError("Size must be greater than 0 and no greater \
                        than the number of digits")
    return [map(int,digits[i:i+size]) for i in range(digitslength - size + 1)]
