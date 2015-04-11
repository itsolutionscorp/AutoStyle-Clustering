#!/usr/bin/env python
 # -*- coding: utf-8 -*-

def sum_of_multiples(n, multiples_of=[3,5]):
	if not isinstance(n,int) or not isinstance(multiples_of, [].__class__):
		raise ValueError("n must be an integer and multiples_of must be a list")
	return sum(set([i for i in range(1,n) for j in multiples_of if j != 0 and i % j == 0]))
