def slices(string, num):
    if num > len(string):
        raise ValueError('Cannot have a slice longer than string length.')
    if num == 0:
        raise ValueError('Cannot have slice length zero.')
    return [[int(n) for n in sl]
            for sl in [string[0+i:num+i]
            for i in range(0, len(string)-num+1)]]
