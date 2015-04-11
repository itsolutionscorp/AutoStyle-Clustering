def distance(a, b):
    assert len(a) == len(b)
    hamming = 0
    for charindex in range(len(a)):
        if a[charindex] != b[charindex]:
            hamming += 1
    return hamming
