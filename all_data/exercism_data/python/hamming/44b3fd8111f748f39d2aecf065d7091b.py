__author__ = 'js'


def distance(strand1, strand2):
    return sum(strand1[index]!=strand2[index] for index in range(len(strand1)))
