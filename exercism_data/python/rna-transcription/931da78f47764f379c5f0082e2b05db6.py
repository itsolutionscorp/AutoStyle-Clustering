def convertStrand(strand):
	if strand == 'G':
		return 'C'
	elif strand == 'C':
		return 'G'
	elif strand == 'T':
		return 'A'
	elif strand == 'A':
		return 'U'

def to_rna(input):
	result = ''
	for i in range(len(input)):
		result += convertStrand(input[i])

	return result
