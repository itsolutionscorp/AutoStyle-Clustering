#!/usr/bin/env python
 # -*- coding: utf-8 -*-

def distance(strand_1, strand_2):
	if not isinstance(strand_1, "".__class__) or type(strand_1) != type(strand_2) or len(strand_1) != len(strand_2):
		raise ValueError("strand_1 and strand_2 must be strings with same length")
	
	hamming_distance = 0
	
	for i in range(len(strand_1)):
		if strand_1[i] != strand_2[i]: 
			hamming_distance+=1
	
	return hamming_distance
