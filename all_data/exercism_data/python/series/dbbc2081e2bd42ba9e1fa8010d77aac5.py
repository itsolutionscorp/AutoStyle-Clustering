def slices(digits, length):

    # check for overly short or overly long length
    if not ( 0 < length <= len(digits) ):
        raise ValueError

    def slicer(j):
        return [int(digits[i + j]) for i in xrange(length)]

    return [slicer(j) for j in xrange(len(digits) - length + 1)]

