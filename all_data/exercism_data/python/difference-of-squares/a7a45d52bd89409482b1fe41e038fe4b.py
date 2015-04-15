#!/usr/bin/env python
# -*- coding: utf-8 -*-


def difference(a):
	return square_of_sum(a) - sum_of_squares(a)


def square_of_sum(a):
	return sum(range(a+1))**2


def sum_of_squares(a):
	return sum([x**2 for x in range(a+1)])	
