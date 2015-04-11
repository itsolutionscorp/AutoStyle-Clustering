#!/usr/bin/python
#############################################
#
# File Name : hamming.py
#
# Last Modified : Tue 28 Oct 2014 02:27:47 AM PDT
#
#############################################

def distance(seq1, seq2):
    if len(seq1) != len(seq2):
        raise Exception("Input sequences of unequal length")

    hamming_count = 0
   
    #for i in xrange(len(seq1)):
    #    if seq1[i] != seq2[i]:
    #        hamming_count += 1

    for strand1, strand2 in zip(seq1,seq2):
        if strand1 != strand2:
            hamming_count += 1
    
    return hamming_count
