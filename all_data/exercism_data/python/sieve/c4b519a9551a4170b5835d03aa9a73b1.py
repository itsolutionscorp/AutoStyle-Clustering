#/usr/bin/env python
def sieve(top):
	prime = []
	composite = []
	for xx in range(2,top+1):
		print xx
		if not xx in composite:
			prime.append(xx)
			for lm in range(top+1)[::xx]:
				composite.append(lm)
	return prime
