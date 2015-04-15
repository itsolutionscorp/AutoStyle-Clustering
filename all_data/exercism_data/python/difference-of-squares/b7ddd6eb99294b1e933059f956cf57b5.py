#!/usr/bin/env python
# -*- coding: utf-8 -*-


def difference(a):
	return square_of_sum(a) - sum_of_squares(a)


def square_of_sum(a):
	return sum(range(a+1))**2


def sum_of_squares(a):
	total = 0
	for i in range(0,a+1):
		total += i**2
	return total	
