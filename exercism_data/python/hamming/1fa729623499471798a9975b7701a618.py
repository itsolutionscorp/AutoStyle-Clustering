# -*- coding: utf-8 -*-


def distance(base, mutation):
	distance = 0
	for b, m in zip(base, mutation):
		if b != m:
			distance += 1
	return distance
