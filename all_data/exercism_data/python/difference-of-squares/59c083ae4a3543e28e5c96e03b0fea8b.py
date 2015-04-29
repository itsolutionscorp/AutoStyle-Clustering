def square_of_sum(nums):
	return pow((sum(list(range(nums + 1)))), 2)

def sum_of_squares(nums):
	return sum((x**2 for x in list(range(nums+1))))

def difference(nums):
	return (square_of_sum(nums) - sum_of_squares(nums))
