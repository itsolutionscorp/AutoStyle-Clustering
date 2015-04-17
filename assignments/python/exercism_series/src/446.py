def slices(digits, size):
    slicelist = []
    if size <= 0:
        raise ValueError("Size is too small for slice.")
    if size > len(digits):
        raise ValueError("Size is too large for slice.")
    for i in range(len(digits) - size + 1):
        slicelist.append(map(int,[x for x in digits[i:i+size]]))
    return slicelist
