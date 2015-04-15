# -*- coding: utf-8 -*-
'''
Hamming Distance.

Author: SprayIdle

This program calculates the Hamming difference between two DNA strands

TESTING:

	hamming_test.py (seperate file) will run numerous test cases
	over the hamming module.
	
REVISION HISTORY:

	24/09/14:	Initial implementation of hamming function.
'''

from operator import ne as not_equal

def hamming(dna_1, dna_2):
	'''(string, string) -> int
	
	Return an int representing the hamming distance of two given DNA
	nucleotide strings.
	'''
	return sum(map(not_equal,dna_1, dna_2))
