def calculate(sentence):
	from operator import add, sub, mul, truediv as divv
	operations = {
		'plus': add,
		'minus':sub,
		'multiplied':mul,
		'divided':divv
	}
	text = sentence.replace('?','').split(' ')
	nums = []
	funcs = []
	for i in text:
		try:
			nums.append(int(i))
		except ValueError:
			try:
				funcs.append(operations[i])
			except KeyError:
				continue
	if len(nums)!=len(funcs)+1:
		raise ValueError("Missing an operation or too many numerical inputs")
	if len(funcs) == 0:
		raise ValueError("Invalid or missing operation.")

	k = nums[0]
	for n in range(len(funcs)):
		k = funcs[n](k,nums[n+1])

	return k
