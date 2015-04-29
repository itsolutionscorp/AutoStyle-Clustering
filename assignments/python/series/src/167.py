def slices(num_str, n):
    ''' raise exception if n is out of bounds
        slice string, convert characters to int as a list '''
    digits = len(num_str)
    if n <= 0 or n > digits:
        raise ValueError('0 < n <= len(num_str)')
    result = []
    for i in range(digits - n + 1):
        result.append([int(j) for j in num_str[i:i+n]])
    return result
