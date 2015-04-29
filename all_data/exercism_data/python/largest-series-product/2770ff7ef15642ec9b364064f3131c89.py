def largest_product(num, s):
	if num == "":
		return 1
	m = 0
	for l in slices(num, s):
		ans = reduce(lambda x, y: x * y, l)
		m = ans if ans > m else m
	return m
def slices(number, jump):
	nums = [int(x) for x in number]
	if jump > len(number):
		raise ValueError("the slice length is bigger then number length")
	return [nums[i:i+jump] for i in range(len(number) - jump + 1)]
