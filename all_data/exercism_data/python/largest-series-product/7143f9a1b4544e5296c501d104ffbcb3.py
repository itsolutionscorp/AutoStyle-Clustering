def slices(nums, slice_size):
	if slice_size > len(nums) or slice_size < 0:
		raise ValueError
		
	slices = [] 
	for i in range(0, len(nums) - slice_size + 1):
		slices.append([int(j) for j in nums[i:i+slice_size]])
	return slices

def largest_product(nums, n):
	s = slices(nums,n)
	max_val = 0
	for i in s:
		product = 1
		for j in i:
			product *= j
		if product > max_val:
			max_val = product
	return max_val
