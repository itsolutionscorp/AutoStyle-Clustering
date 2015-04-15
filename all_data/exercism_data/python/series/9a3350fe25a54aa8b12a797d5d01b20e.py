def make_int(piece):
    return [int(d) for d in piece]

def slices(str, n):
    if n > len(str) or n < 1:
        raise ValueError("n is too long or too short")
    return [make_int(str[index:index + n]) for index in xrange(len(str) + 1 - n)]
