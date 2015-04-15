def slices(string, n):
	nums = []
	output = []
	templist = []
	
	for letters in string:
		nums.append(int(letters))

	if n > len(nums):
		raise ValueError('Length is too long')
	elif n == 0:
		raise ValueError ('Length must be greater than zero')
		
	for i in range(len(nums) - n + 1):
		for element in range(n):
			templist.append(nums[element+i])
		output.append(templist)
		templist = []
		
	return output
