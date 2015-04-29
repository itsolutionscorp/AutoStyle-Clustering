# -*- coding: utf-8 -*-

def distance(dna_alpha, dna_beta):
	ans = 0
	for (x, y) in zip(dna_alpha, dna_beta):
		if x != y:
			ans += 1
	
	return ans
