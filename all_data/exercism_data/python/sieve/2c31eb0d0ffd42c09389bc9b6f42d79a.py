def sieve(num):
	nums = list(xrange(2, num+1))
	for i in nums:
		nums = filter(lambda x: x % i or x == i, nums)

	return nums
