def slices(nums, n):
	if n > len(nums) or n <= 0:
		raise ValueError("Inappropriate length argument")
	a = []
	for i in range(0, len(nums)-n+1): 
		b = []						  
		for j in range(i, i+n):
			b.append(int(nums[j]))
		a.append(b)
	return a
