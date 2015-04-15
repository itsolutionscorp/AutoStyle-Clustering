def _gcd(a,b):
	# Adding in a hack to ensure proper GCD definition used.
	# Mathematical def: gcd(a,b) == gcd(|a|,|b|)
	c = int((a*a)**.5)
	d = int((b*b)**.5)
	while d:
		c,d = d, c%d
	return c

def primitive_triplets(b):
	# Bit of error checking
	if b%4:
		raise ValueError("B must be divisible by 4")
	triplets = set()
	# Implement algorithm for pythagorean triplets
	for i in range(2,b+1):
		for j in range(1,i+1):
			l = i**2-j**2
			k = 2*i*j
			m = i**2+j**2
			nums = sorted([l,k,m])
			if _gcd(i,j) == 1 and (i-j)%2 > 0 and (b in  nums) and is_triplet(set(nums)):
				triplets.add(tuple(nums))
	return triplets

def triplets_in_range(start,end):
	tripList = set
	# Worked on this set for days. Moving to direct approach.
	# for b in range(end+1):
	# 	if not b%4:
	# 		smallTrips = primitive_triplets(b)
	# 		smallTripsParsed = smallTrips.copy()
	# 		for i in smallTrips:
	# 			if max(i) >= end:
	# 				smallTripsParsed.remove(i)
	# 			elif min(i) <= start:
	# 				smallTripsParsed.remove(i)

	# 		smallTrips = smallTripsParsed.copy()
	# 		if len(smallTrips)==0: continue
	# 		for i in smallTripsParsed:
	# 			if max(i) <= end:
	# 				k = 1
	# 				while k * max(i) <=end:
	# 					iMore = tuple(j*k for j in i)
	# 					k+=1
	# 					tripList.add(iMore)
	for i in range(start+2,end+1):
		for j in range(start+1,i):
			for k in range(start,j):
				if i**2 == k**2 + j**2:
					tripList.add((k,j,i))
	return tripList

def is_triplet(setIn):
	nums = list(setIn)
	nums.sort()
	return nums[0]**2+nums[1]**2 == nums[2]**2
