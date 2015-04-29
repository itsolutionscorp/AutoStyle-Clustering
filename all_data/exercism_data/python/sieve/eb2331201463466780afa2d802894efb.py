import math

def sieve(n):
	res = []
	init = {2: [2], 3: [2, 3], 4: [2, 3], 5: [2, 3, 5], 6: [2, 3, 5], 7: [2, 3, 5, 7]}
	if n < 2:
		return res
	elif n < 8:
		return init[n]
	candidates = [2, 3, 5, 7] + [
		x
		for x in xrange(4, n + 1)
		if x % 2 and x % 3 and x % 5 and x % 7
	]
	max_check = int(math.sqrt(n)) + 1

	def eliminate_from(i, val):
		for j in xrange(i, len(candidates)):
			if candidates[j] is not None and candidates[j] % val == 0:
				candidates[j] = None

	for i in xrange(len(candidates)):
		val = candidates[i]
		if val is not None:
			if val <= max_check:
				eliminate_from(i, val)
			res.append(val)
	return res
