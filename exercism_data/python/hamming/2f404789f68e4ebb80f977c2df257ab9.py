'''
Created on Sep 24, 2014

@author: Adam Smith
'''

from itertools import zip_longest

def hamming(_from, _to):
    pairwise = zip_longest(_from, _to, fillvalue="!")
    # build a zip where next(pairwise) returns _frombase, _tobase, extending
    # the shorter sequence with "!" (guaranteed not to be in the longer seq)
    return sum(1 if not from_base==to_base else 0 for from_base,to_base in pairwise)

    
