def slices(in_str, n):
    if not 0 < n <= len(in_str):
        raise ValueError("Size must between 0 and length of input")
    
    return [list(map(int, in_str[i:i+n])) for i in range(len(in_str) - n + 1)]
