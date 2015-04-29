import itertools
def slices(series, number):
    return [i for i in itertools.permutations(series, number)]

