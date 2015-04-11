hexkey = {}
for i in range(10):
	hexkey[str(i)] = i
char = 'ABCDEF'
for i,h in enumerate(char):
	hexkey[h] = i + 10
	hexkey[h.lower()] = i + 10

def hexa(hexes):
	dec = 0
	try:
		for h in hexes:
			dec *= 16
			dec += hexkey[h]
		return dec
	except KeyError:
		raise ValueError('Not a valid hex string.')
