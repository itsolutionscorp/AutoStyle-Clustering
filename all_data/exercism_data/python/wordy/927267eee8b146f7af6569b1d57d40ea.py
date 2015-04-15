ops = {'plus': lambda x, y:x + y, 'minus': lambda x, y: x - y, 'raised': lambda x, y: x**y,
'multiplied': lambda x, y: x * y, 'divided': lambda x, y: x // y}

def calculate(sentence):
	if not sentence.startswith('What is ') or not sentence.endswith('?'):
		raise ValueError

	parts = sentence[8:-1].split()
	parts.reverse()
	accumulator = int(parts.pop())
	while parts:
		op, b = get_next(parts)
		accumulator = op(accumulator, b)

	return accumulator

def get_next(parts):
	op = parts.pop()
	if op in ops:
		if op == 'multiplied' or op == 'divided':
			parts.pop()
			value = int(parts.pop())
		elif op == 'raised':
			del parts[-2:]
			value = int(parts.pop()[:-2])
			parts.pop()
		else:
			value = int(parts.pop())
		return ops[op], value
	raise ValueError
