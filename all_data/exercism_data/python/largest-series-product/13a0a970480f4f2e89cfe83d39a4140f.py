

def slices(seq, choose):
	"""
	Returns all possible consecutive sequences of choose numbers in seq
	"""
	if choose > len(seq):
		raise ValueError("Sequence not long enough")
	answers = []
	buffer = []
	for i in range(len(seq) - choose + 1):
		for j in range(i,i+choose):
			buffer.append(int(seq[j]))
		answers.append(buffer)
		buffer = []
	return answers
	
def largest_product(seq, choose):
	"""
	Returns the largest product of choose consecutive numbers in seq
	"""	
	possibilities = slices(seq, choose)
	max = 0
	for slice in possibilities:
		if product(slice) > max:
			max = product(slice)
	return max
	
def product(seq):
	"""
	Returns the product of all numbers in seq
	"""
	a = 1
	for num in seq:
		a *= num
	return a
