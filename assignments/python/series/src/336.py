def slices(seq, n):
    if n <= 0 or int(n) != n:
        raise ValueError("{} is not a positive integer".format(n))
    if n > len(seq):
        raise ValueError("length of '{}' greater than {}".format(str, n))
    return [seq[i:i+n] for i in xrange(len(seq) - n + 1)]
