__author__ = 'jimblackler'
def slices(chars, length):
    if length < 1 or length > len(chars):
        raise ValueError()
    digits = [int(x) for x in chars]
    return [digits[n - length:n] for n in xrange(length, len(chars) + 1)]
