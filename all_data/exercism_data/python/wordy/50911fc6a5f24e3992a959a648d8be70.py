def calculate(input):
	problem = input[8:-1]
	if input[:8] != 'What is ' or input[-1] != '?':
		raise ValueError('question format incorrect')
	dict = {'plus':'+', 'minus':'-', 'divided by':'/', 'multiplied by':'*','th power':'','raised to the':'**'}
	for key in dict:
		problem = problem.replace(key,dict[key])
	problem = problem.split()
	try:
		res = int(problem[0])
	except:
		raise ValueError('question format incorrect')
	for i in range(1,len(problem)-1,2):
		current = '%s%s%s' % (res,problem[i],problem[i+1])
		try:
			res = eval(current)
		except:
			raise ValueError('question format incorrect')
	return res
