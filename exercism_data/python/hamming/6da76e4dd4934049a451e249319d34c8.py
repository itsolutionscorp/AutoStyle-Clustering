#!/bin/python

def hamming(left, right):
	count = 0

	for i in range(min(len(left), len(right))):
		if left[i] != right[i]:
			count += 1

	return count + abs(len(left) - len(right))
