#!/usr/bin/python
# -*- coding: UTF-8 -*-

import itertools

def hamming(dna_listold,dna_listnew):
    count = 0

    for old, new in itertools.izip_longest(dna_listold, dna_listnew):
        if old == new:
            pass
        elif old != new:
            count += 1
    return count 
    
    



print hamming("GAGCCTACTAACGGGAT","CATCGTAATGACGGCCT")
