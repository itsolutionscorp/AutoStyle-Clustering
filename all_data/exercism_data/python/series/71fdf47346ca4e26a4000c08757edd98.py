

def slices(digits, n):
    if n > len(digits) or n == 0:
        raise ValueError("n must be an integer less than the length of digits and greater than 0")
    sliced_d = map(int, digits[:])
    return [sliced_d[i:i+n] for i in range(len(sliced_d) - n + 1)]
        
        
