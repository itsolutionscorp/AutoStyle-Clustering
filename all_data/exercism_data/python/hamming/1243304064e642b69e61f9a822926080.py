#!/usr/bin/env python
# coding=utf-8
"""
Show hamming distance between 2 dna strands
"""


def distance(strand1, strand2):
    """
    Calculate hamming idstance
    @param strand1: str
    @param strand2: str
    @return: int
    """
    real_distance = 0
    for position in range(len(strand1)):
        real_distance += strand1[position] != strand2[position]
    return real_distance


if __name__ == '__main__':
    pass
