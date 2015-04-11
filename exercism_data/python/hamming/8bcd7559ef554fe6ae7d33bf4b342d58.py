#!/usr/bin/env python3
# -*- coding: utf-8 -*-

def distance(left, right):

	"""
	Return the Hamming distance between to strings.

	Note: No error checking occurs. The strings have to be the same length.
	"""

	distance = 0

	for i in range(0, len(left)):
		if left[i] != right[i]:
			distance += 1

	return distance
