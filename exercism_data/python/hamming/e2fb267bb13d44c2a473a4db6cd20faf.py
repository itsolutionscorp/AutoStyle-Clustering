# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)

def distance(orig, final):
    """ Calculates hamming distance between two codes, by checking how many
        point mutations have occurred"""
    distance = 0
    for o, f in zip(orig, final):
        distance += 1 if o != f else 0
    return distance
