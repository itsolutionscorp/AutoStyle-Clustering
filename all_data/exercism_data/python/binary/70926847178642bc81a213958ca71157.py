def parse_binary(bin):
	if bin.strip('01'):
		raise ValueError('Input not binary')
	n = len(bin)
	return sum([1<<(n-i-1) for i in range(n) if bin[i] == '1'])
