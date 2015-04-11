__author__ = 'Flavio Miranda'


def distance(strand, mutation):
    return sum(1 for x, y in zip(strand, mutation) if x != y) + (
    len(strand) - len(mutation) if len(strand) > len(mutation) else len(mutation) - len(strand))
