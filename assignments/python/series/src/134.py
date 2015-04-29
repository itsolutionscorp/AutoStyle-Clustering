import itertools
def slices(seq, lenth):
    return [perm for perm in itertools.permutations(seq, lenth)]
