def to_rna(self):
	x = ''
	for i in self:
		if i == 'G':
			x = x + 'C'
		elif i == 'C':
			x = x + 'G'
		elif i == 'T':
			x = x + 'A'
		elif i == 'A':
			x = x + 'U'
	return x
