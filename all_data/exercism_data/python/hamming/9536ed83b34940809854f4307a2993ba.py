__author__ = 'js'


def distance(strand1, strand2):
    count = 0
    for idx, val in enumerate(strand1):
        if strand1[idx] != strand2[idx]:
            count += 1
    return count
