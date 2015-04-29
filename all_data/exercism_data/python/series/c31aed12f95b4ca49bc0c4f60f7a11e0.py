from exceptions import ValueError

def slices(string, size):
    # sanity check
    if size < 1 or size > len(string):
        raise ValueError("size has to be greater than zero and smaller or equal to the length of the string")
    # the slicing
    slices = [list(string[x:x+size]) for x in xrange(len(string)-size+1)]
    # convert strings to integers
    return [map(int, element) for element in slices]
