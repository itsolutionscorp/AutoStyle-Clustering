def slices(s, n):
    if n > len(s):
        raise ValueError('Cannot have slice longer than string length')
    elif n <= 0:
        raise ValueError('Slice must be of positive length')
    else:
        series = []
        j=0
        while j + n <= len(s):
            series.append([int(s[i]) for i in xrange(j,j+n)])
            j += 1 
        return series
