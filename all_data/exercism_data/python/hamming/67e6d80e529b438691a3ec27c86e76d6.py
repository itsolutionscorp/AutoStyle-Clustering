__author__ = "Blackfry"


def distance(strand1, strand2):
    h_dist = 0
    for i in range(len(strand1)):
        if strand1[i] != strand2[i]:
            h_dist += 1
    return h_dist
