#!/usr/bin/env python
 # -*- coding: utf-8 -*-

from functools import reduce
 
def largest_product(digits, n):
	__check_arguments(digits,n)
	slices_list = slices(digits, n)
	max_product = __product(slices_list[0])
	for i in slices_list[1:]:
		i_product = __product(i)
		if i_product > max_product:
			max_product = i_product
	
	return max_product

def __product(list):
	return reduce(lambda x,y: x*y, list) if list else 1
 
def slices(digits, n):
	__check_arguments(digits, n)
	return [ list(map(int, digits[i:i+n])) for i in range(0, len(digits) - n + 1)]

	
def __check_arguments(digits, n):
	if not isinstance(digits, "".__class__) or not isinstance(n, int):
		raise ValueError("digits must be a string and n an integer")
	
	if n > len(digits):
		raise ValueError("n must be less or equal than the number of elements in digits")
