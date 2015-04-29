def slices(number_string, length):
    if length == 0 or length > len(number_string):
        raise ValueError("Bad index given")
    res = []
    for i in xrange(0, len(number_string) - length + 1):
        res.append([int(x) for x in number_string[i:i + length]])
    return res
