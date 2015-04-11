__author__ = 'Diana'


def slices(s, length):
    if length > len(s):
        raise ValueError("Incorrect slice length provided:", length)
    else:
        tuples = []
        slices = list(s[i:i+length] for i in range(0, len(s) - length + 1))
        for slice in slices:
            tuples.append(map(int,list(slice)))
        return tuples

def largest_product(s, length):
    largest_product = 0
    tuples = slices(s, length)
    for tuple in tuples:
        product = 1
        for i in tuple:
            product *= i
        if product > largest_product:
            largest_product = product
    return largest_product
