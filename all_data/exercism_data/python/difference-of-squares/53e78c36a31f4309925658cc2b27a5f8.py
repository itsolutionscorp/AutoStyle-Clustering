#!/usr/bin/python3 -V
# -*- coding: utf-8 -*-

import math

def difference(num):
	return math.fabs(square_of_sum(num) - sum_of_squares(num))

def square_of_sum(num):
	return sum([x for x in range(num + 1)])**2
	
def sum_of_squares(num):
	return sum([y**2 for y in range(num + 1)])
