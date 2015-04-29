def slices(numStr, n):

    if n > len(numStr) or n <= 0:
        raise ValueError

    return [[int(intc) for intc in numStr[i:i+n]] for i in xrange(len(numStr)-n+1)]
