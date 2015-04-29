def slices(inStr, n):
    if not 0 < n <= len(inStr):
        raise ValueError("Invalid slice length")
    return [list(map(int, inStr[i:i+n])) for i in range(len(inStr)+1-n)]
