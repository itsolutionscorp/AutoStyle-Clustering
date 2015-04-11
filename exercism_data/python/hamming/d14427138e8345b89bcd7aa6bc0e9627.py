def hamming(a, b):
    return map((lambda x: x[0] == x[1]), zip(a, b)).count(False)
