codes = ['wink', 'double blink', 'close your eyes', 'jump']

def handshake(code):

	if type(code) == str:
		try:
			code = int(code, 2)
		except ValueError:
			code = 0

	if code < 0:
		code = 0

	operations = [
		codes[n]
		for n in xrange(4)
		if code & 2**n
	]

	if code & 16:
		operations.reverse()

	return operations

def code(ops):

	try:
		indices = [
			2**codes.index(operation)
			for operation in ops
		]
	except ValueError:
		return '0'

	code = sum(indices)
	if len(indices) > 1 and indices[0] > indices[1]:
		code += 16

	return bin(code)[2:]
