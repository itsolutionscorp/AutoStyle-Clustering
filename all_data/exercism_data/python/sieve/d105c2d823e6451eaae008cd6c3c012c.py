import numpy as np
def sieve(n):
	nums = np.array(range(2,n+1))
	counter = 0
	while True:
		x = nums[counter]
		y = np.array([i % x for i in nums])
		y[counter] = 1
		nums = nums[np.where(y != 0)] 
		counter = counter + 1
		if (counter == len(nums)-1): break

	return nums.tolist()
