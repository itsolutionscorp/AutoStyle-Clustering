def slices(string, length):
    if length > len(string) or length < 1:
        raise ValueError('Length must be between 1 and the string length.')
    return [string[i:i+length] for i in xrange(0, len(string) - length + 1)]
