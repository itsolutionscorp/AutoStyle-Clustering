import operator

def slices(string, length):
    if length > len(string) or length < 1:
        raise ValueError('Length must be between 1 and the string length.')
    return [[int(number) for number in string[i:i+length]] for i in xrange(0, len(string) - length + 1)]

def prod(iterable):
    return reduce(operator.mul, iterable, 1)

def largest_product(string, length):
    if length == 0:
        return 1
    else:
        return max([prod(slice) for slice in slices(string, length)])
