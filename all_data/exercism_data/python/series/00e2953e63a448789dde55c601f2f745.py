def slices(digits, size):
    
    digitslength = len(digits)
    
    # Check if size is too small or too large to slice with given digits
    if size == 0 or size > digitslength:
        raise ValueError("Size must be greater than 0 and no greater \
                        than the number of digits")
    
    # Use list comprehension to find all slices then map each value to an integer
    return [map(int,digits[i:i+size]) for i in range(digitslength - size + 1)]
