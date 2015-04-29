#!/usr/bin/env python
import sys

class SumOfMultiples:
	def __init__(self, *numbers):
		self.nums = numbers if numbers else [3, 5]


	def to(self, limit):
		"""
			Return sum of multiples of 3, 5 below the limit.
		"""
		res = 0
		i = 0

		while i < limit:
			if any(i%n == 0 for n in self.nums):
				res += i
			i += 1
		return res
