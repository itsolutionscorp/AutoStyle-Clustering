operations = {1: 'wink', 2: 'double blink', 4: 'close your eyes', 8: 'jump'}
reverse_ops = {v: k for k, v in operations.items()}

def handshake(num):
	result = []

	if type(num) == str:
		try:
			num = int(num, 2)
		except:
			return result

	if num < 0:
		return result

	for i in sorted(operations):
		if num & i:
			result.append(operations[i])

	if num & 16:
		result.reverse()

	return result

def code(operations):
	result = []
	for op in operations:
		if op not in reverse_ops:
			return '0'
		result.append(reverse_ops[op])
	if result[0] > result[-1]:
		result.append(16)
	return bin(sum(result))[2:]
