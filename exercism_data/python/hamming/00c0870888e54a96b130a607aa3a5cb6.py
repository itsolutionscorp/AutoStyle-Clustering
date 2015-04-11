#!/usr/bin/python
# exercism python 5: hamming

def distance(dna_one, dna_two):
    distance = 0
    for index in xrange(len(dna_one)):
        if dna_one[index] != dna_two[index]:
            distance += 1
    return distance
