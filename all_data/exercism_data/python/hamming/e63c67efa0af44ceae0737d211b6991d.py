# Hamming distance

def distance(code0, code1):
	return len(filter(lambda code: code[0] != code[1], zip(code0, code1)))
