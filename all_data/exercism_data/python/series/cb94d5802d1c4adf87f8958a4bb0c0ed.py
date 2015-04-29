def slices(seq, size):
    if not size or size > len(seq):
        raise ValueError('Size must not be zero, and be smaller than seq.')
    output = []
    for i, _ in enumerate(seq):
        tmp = [int(x) for x in seq[i:i+size]]
        if len(tmp) < size:
            break
        output.append(tmp)
    return output
