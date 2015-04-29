import operator


def distance(dna1, dna2):
    return map(operator.eq, dna1, dna2).count(False)
