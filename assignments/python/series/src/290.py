def slices(s, l):
    if l > len(s) or l <= 0:
        raise ValueError
    return [map(int, s)[x: y]
            for x, y in zip(xrange(0, len(s)+1), xrange(l, len(s)+1))]
