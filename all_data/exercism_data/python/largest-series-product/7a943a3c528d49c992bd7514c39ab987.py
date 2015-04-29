#/usr/bin/env python
def slices(nums, lens):
	if lens > len(nums): raise ValueError('Series length is larger than string length')
	elif lens < 1: return [[1]]
	elif lens == len(nums):
		for yy in list(nums):
			yy = int(yy)
		return nums
	else:
		yeup = []
		yah = []
		for nnm in range(len(nums)-lens+1):
			yeup.append(nums[nnm:lens+nnm])
		print yeup
		print [list(xx) for xx in list(yeup)]
		for xx in range(len(yeup)):
			yah.append([])
			for yy in yeup[xx]:
				yah[xx].append(int(yy))
		return yah

def product(lis):
	p = 1
	for lm in lis:
		p *= lm
	return p

def largest_product(nums,lens):
	produ = 0
	for mnm in slices(nums,lens):
		if product(mnm) > produ:
			produ = product(mnm)
	return int(produ)
