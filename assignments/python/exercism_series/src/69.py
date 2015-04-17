def slices(s,n):
    if n == 0:
        raise ValueError('0 is not valid length for slices')
    elif n > len(s):
        raise ValueError('%d is greater than len(s): %d' % (n,len(s)))
    else:
        _slices = []
        for i in range(len(s)-n+1):
            _slices.append([int(x) for x in ' '.join(s[i:i+n]).split()])
        return _slices
