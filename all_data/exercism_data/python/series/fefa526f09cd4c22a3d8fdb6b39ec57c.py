def slices(digits, size):
    
    slicelist = []
    
    # Check if size is too small or too large to slice with given digits
    if size <= 0:
        raise ValueError("Size is too small for slice.")
    if size > len(digits):
        raise ValueError("Size is too large for slice.")
    
    # Use list comprehension to find all slices then map each value to an integer
    for i in range(len(digits) - size + 1):
        slicelist.append(map(int,[x for x in digits[i:i+size]]))
    
    return slicelist
