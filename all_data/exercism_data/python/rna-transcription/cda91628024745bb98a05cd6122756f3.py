def to_rna(DNA):
	code_list = []
	for piece in DNA:
		if piece == 'G':
			code_list.append('C')
		elif piece == 'C':
			code_list.append('G')
		elif piece == 'T':
			code_list.append('A')
		else: code_list.append('U')
	return "".join(code_list)
