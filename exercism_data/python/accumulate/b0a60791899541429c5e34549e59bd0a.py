def accumulate(nums, op):
	out = []
	for num in nums:
		out.append(op(num))
	return out
