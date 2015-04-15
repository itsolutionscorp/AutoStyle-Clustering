def trans(c):
	if c == 'A':
		return 'U'
	if c == 'G':
		return 'C'
	if c == 'C':
		return 'G'
	if c == 'T':
		return 'A'
def to_rna(string):
	code = []
	for i in range(len(string)):
		code.append(trans(string[i]))
	code=''.join(code)
	return code
