#/usr/bin/env python
def slices(nums, lens):
	if lens > len(nums):
		raise ValueError('Series length is larger than string length')
	elif lens == len(nums):
		nah = [[]]
		for yy in nums:
			nah[0].append(int(yy))
		return nah
	elif lens < 1:
		raise ValueError('Series length too short')
	else:
		yeup = []
		yah = []
		for nnm in range(len(nums)-lens+1):
			yeup.append(nums[nnm:lens+nnm])
		print yeup
		for xx in range(len(yeup)):
			yah.append([])
			for yy in yeup[xx]:
				yah[xx].append(int(yy))
		return yah
