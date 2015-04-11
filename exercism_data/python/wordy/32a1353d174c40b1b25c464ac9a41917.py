import operator

ops = {
	'plus':			operator.add,
	'minus':		operator.sub,
	'multiplied':	operator.mul,
	'divided':		operator.div
}

def calculate(s):

	if not s.startswith('What is '):
		raise ValueError()

	#	skip "what is" and skip "?"
	s = s[8:-1].split()

	value = 0
	oper = operator.add

	for token in s:

		if token.isalpha():
			if oper:
				if token != 'by':
					raise ValueError()
			elif token in ops:
				oper = ops[token]
			else:
				raise ValueError()

		else:
			if oper:
				value = oper(value, int(token))
				oper = None
			else:
				raise ValueError()

	return value
