def hamming(a, b):
    return map((lambda x: x[0] == x[1]), map(None, a, b)).count(False)
