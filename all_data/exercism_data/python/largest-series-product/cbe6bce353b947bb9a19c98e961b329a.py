def slices(what, length):
    if len(what) == length == 0:
        return [[1]]

    if len(what) < length or length <= 0:
        raise ValueError("Slice must be between 0 and " + str(len(what)) + ". Illegal slice: " + str(length))

    return [[int(x) for x in what[i:i + length]] for i in range(len(what) - length + 1)]


def largest_product(what, length):
    return max(reduce((lambda t, u: t * u), v) for v in (slices(what, length)))
