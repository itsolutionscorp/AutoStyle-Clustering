def slices(num_str, n):
    ''' raise exception if n is out of bounds
        slice string, convert characters to int as a list '''
    try:
        if n <= 0 or n > len(num_str):
            raise ValueError('0 < n <= len(num_str)')
    except ValueError:
        raise
    result = []
    for i in range(len(num_str)-n+1):
        partial = list(num_str[i:i+n]) 
        result.append([int(j) for j in partial])
    return result
