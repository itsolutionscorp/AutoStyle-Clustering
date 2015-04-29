def parse_binary(s):
	bits = map(int, s)
	if max(bits) > 1:
		raise ValueError
	return sum(b<<i for i,b in enumerate(bits[::-1])) 
