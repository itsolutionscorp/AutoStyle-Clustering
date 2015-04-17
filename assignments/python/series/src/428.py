def slices(str, n):
    if n <= 0 or int(n) != n:
        raise ValueError("{} is not a positive integer".format(n))
    if n > len(str):
        raise ValueError("length of '{}' greater than {}".format(str, n))
    lst = []
    for i in xrange(len(str) - n + 1):
        lst.append([int(c) for c in str[i:i+n]])
    return lst
