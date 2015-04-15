#!/usr/bin/env python
 # -*- coding: utf-8 -*-
 
def difference(n):
	if not isinstance(n, int):
		raise ValueError("n must be an integer")
	return abs(sum_of_squares(n) - square_of_sum(n))

	
def square_of_sum(n):
	if not isinstance(n, int):
		raise ValueError("n must be an integer")
	sum = n*(n+1)/2
	return sum**2
	

def sum_of_squares(n):
	if not isinstance(n, int):
		raise ValueError("n must be an integer")
	sum = 0
	for i in range(n):
		sum+=(i+1)**2
	return sum
