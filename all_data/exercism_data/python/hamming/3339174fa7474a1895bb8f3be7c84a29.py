import itertools


def distance(strand1, strand2):
    print sum(itertools.imap(str.__ne__, strand1, strand2))
