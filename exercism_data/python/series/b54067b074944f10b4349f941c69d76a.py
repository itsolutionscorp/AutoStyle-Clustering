def slices(string, n):
    if not n or n > len(string):
        raise ValueError
    slicelist = []
    for i in xrange(len(string)+1-n):
        slicelist.append(string_to_list(string[i:i+n]))
    return slicelist


def string_to_list(string):
    return [int(c) for c in string]
