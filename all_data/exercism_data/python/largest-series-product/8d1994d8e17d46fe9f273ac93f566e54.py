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


def largest_product(seq, size):
    if not seq or not size:
        return 1
    series = slices(seq, size)
    result = 0
    for i in series:
        test = reduce(lambda x, y: x*y, i)
        if test > result:
            result = test
    return result
