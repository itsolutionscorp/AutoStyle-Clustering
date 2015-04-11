def to_rna(dnaString):
	ns = []
	s = list(dnaString)
	for c in s:
		if(c == 'C'):
			ns.append('G')
		elif (c == 'G'):
			ns.append('C')
		elif(c == 'T'):
			ns.append('A')
		elif(c == 'A'):
			ns.append('U')
		else:
			ns.append(c)
	return ''.join(ns)
