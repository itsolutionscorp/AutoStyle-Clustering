def slices(what, length):
    if len(what) < length or length <= 0:
        raise ValueError("Slice must be between 0 and " + str(len(what)) + ". Illegal slice: " + str(length))

    start = 0
    s = []
    while start + length <= len(what):
        s.append([int(x) for x in what[start:start + length]])
        start += 1

    return s
