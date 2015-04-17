from exceptions import ValueError
def slices(string, size):
    if size < 1 or size > len(string):
        raise ValueError("size has to be greater than zero and smaller or equal to the length of the string")
    slices = [list(string[x:x+size]) for x in xrange(len(string)-size+1)]
    return [map(int, element) for element in slices]
