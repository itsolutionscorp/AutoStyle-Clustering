#!/usr/bin/python2


def distance(st1, st2):
    '''We use zip to stick together the two string that are in fact
    sequences, and then walk through the correspondent composing tuple pairs
    evaluating the distance. Note that using zip we also stick to the
    shortest of the sequence, the chars in the longer one evntually are
    discarded'''
    d = 0
    for (a, b) in zip(st1, st2):
        d = d + 1 if a != b else d
    return d
