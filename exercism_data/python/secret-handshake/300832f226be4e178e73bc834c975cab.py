GESTURES = ["wink", "double blink", "close your eyes", "jump"]

REVERSE_BIT = 4

def handshake(n):
	try:
		n = int(n, 2) if isinstance(n, str) else n
	except ValueError:
		return []
	if n < 0:
		return []
	gestures = [GESTURES[i]
		for i in range(len(GESTURES)) if n>>i & 1 ]
	return gestures[::-1] if n >> REVERSE_BIT & 1 else gestures

def code(gestures):
	try:
		bits = map(GESTURES.index, gestures)
	except ValueError:
		return '0'
	if bits != sorted(bits):
		bits.append(REVERSE_BIT)
	return bin(sum([1<<shift for shift in bits]))[2:]
		
