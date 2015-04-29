__author__ = 'dmwoods'


def distance(strand1, strand2):
    return len([(x,y) for x,y in zip(strand1,strand2) if x != y])
