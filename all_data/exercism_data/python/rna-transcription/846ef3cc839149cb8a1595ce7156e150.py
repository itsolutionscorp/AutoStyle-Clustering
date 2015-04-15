def to_rna(dna):
	answer = ''
	for letter in dna:
		if d == 'G':
			answer = answer + 'C'
		elif d == 'C':
			answer = answer + 'G'
		elif d == 'T':
			answer = answer + 'A'
		elif d == 'A':
			answer = answer + 'U'

	return answer
