def distance(first, second):
    ''' Return the number of differences between first and second
    strings '''
    if len(first) != len(second):
        raise ValueError('Strands must be of equal length')
    dist_func = lambda x, y: 0 if x == y else 1
    return sum(map(dist_func, first, second))
