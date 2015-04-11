#/usr/bin/env python
def sum_of_multiples(top, factors = [3, 5]):
	multis = []
	facts = [dd for dd in factors if dd != 0]
	for hd in facts:
		for xh in range(0,top,hd):
			if not xh in multis: multis.append(xh)
	return sum(multis)
