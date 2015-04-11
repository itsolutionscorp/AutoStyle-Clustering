# -*- coding: utf-8 -*-
"""
Created on Tue Oct 28 11:26:17 2014

@author: DiCar
"""
#define the function of finding the hamming distance of two DNA sequences
def distance(sequence1, sequence2):
    #define hamming_distance variable
    hamming_distance = 0
    #convert sequence 1 and 2 into lists of nucleotides
    list1 = list(sequence1)
    list2 = list(sequence2)
    #set the variable nucleotide_number for calling nucleotides along the list
    nucleotide_number = -1
    for item in list1:
        nucleotide_number += 1
        #compare list[nucleotide_number] of both lists
        if list1[nucleotide_number] != list2[nucleotide_number]:
            hamming_distance += 1
    return hamming_distance
    
