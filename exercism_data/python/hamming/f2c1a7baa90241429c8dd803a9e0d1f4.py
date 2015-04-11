# -*- coding: utf-8 -*-

#calculates hamming distance between 2 dna strands.
def distance(strand_a, strand_b):
    
    hamming_count = 0
    
    if len(strand_a) == len(strand_b):
        if strand_a.isalpha() and strand_b.isalpha():
            #can compare
            for i in range(len(strand_a)):
                if strand_a[i] != strand_b[i]:
                    hamming_count += 1
    
    return hamming_count
