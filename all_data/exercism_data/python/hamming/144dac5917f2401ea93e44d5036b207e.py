import operator

def distance(strand1, strand2):
    return sum(map(operator.ne, strand1, strand2))
