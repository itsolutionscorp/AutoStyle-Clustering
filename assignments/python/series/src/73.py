def slices(sequence, n):
    if n == 0 or n > len(sequence):
        raise(ValueError)
    else:
        return filter(lambda x: len(x) == n, [[int(y) for y in list(sequence)][x:x+n] for x in range(len(sequence))])
