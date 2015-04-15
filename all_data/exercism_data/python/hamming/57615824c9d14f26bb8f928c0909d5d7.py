def distance(a, b):
    counter = 0
    for i in xrange(len(a)):
        if a[i] != b[i]:
            counter += 1
    return counter
