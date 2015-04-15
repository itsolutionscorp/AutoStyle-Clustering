def sieve(limit):
	if limit <= 2:
		raise ValueError

	nums = [0]*(limit-1)
	primes = []

	for i in range(2,limit+1):
		if nums[i-2] == 0:
			primes.append(i)
			for k in range(i+i, limit+1, i):
				nums[k-2] = 1

	return primes
