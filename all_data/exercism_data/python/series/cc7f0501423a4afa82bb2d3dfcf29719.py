def slices(seq, n):
	if 0 < n <= len(seq):
		nums = list(int(c) for c in seq)
		return list(list(nums[i:i+n]) for i in range(0, len(nums) - n + 1))
	else:
		raise ValueError()
