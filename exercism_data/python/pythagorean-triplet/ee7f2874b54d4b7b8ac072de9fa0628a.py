def is_triplet(triplet):
	if len(triplet) != 3:
		raise ValueError('Triplets consist of three values, must pass exactly 3 numbers.')
	else:
		nums = []
		for num in triplet:
			nums.append(num)

		nums.sort()

		return nums[0]**2 + nums[1]**2 == nums[2]**2



def primitive_triplets(b):
	mn = b/2
	factors_mn = factors(mn)
	triplets = set()
	for factor in factors_mn:
		n = factor[0]
		m = factor[1]
		a = (m**2 - n**2)
		b = (2*m*n)
		c = (m**2 + n**2)
		if a%2 == 1 and b%4 == 0 and c%2 == 1:
			if a > b:
				triplets.add((b, a, c))
			else:
				triplets.add((a, b, c))

	return triplets

def factors(n):    
    return set(((i, n/i) for i in xrange(1, int(n**0.5) + 1) if n % i == 0))

def triplets_in_range(min_ran, max_ran):
	triplets = set()
	for i in range(min_ran, max_ran + 1):
		
		mn = i/2
		factors_mn = factors(mn)
		for factor in factors_mn:
			n = factor[0]
			m = factor[1]
			a = (m**2 - n**2)
			b = (2*m*n)
			c = (m**2 + n**2)
			if c <= max_ran:
				if a >= min_ran and b >= min_ran:
					small_num = a if a<b else b
					large_num = a if a>b else b

					triplets.add((small_num, large_num,c))

	return triplets

print is_triplet((3,4,5))
print triplets_in_range(56, 95)
