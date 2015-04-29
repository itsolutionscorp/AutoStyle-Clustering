from exceptions import ValueError

def slices(string, size):
    # sanity check
    if size < 1 or size > len(string):
        raise ValueError("size has to be greater than zero and smaller or equal to the length of the string")
    # convert string into list of integers
    string = [int(c) for c in string]
    # the slicing
    return [string[x:x+size] for x in xrange(len(string)-size+1)]
    
