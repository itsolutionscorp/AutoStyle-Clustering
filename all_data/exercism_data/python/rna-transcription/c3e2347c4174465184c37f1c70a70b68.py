def to_rna(dna):
	values = []
	for letter in dna:
		values.append(letter)
	answer = ''
	for d in values:
		if d == 'G':
			answer = answer + 'C'
		elif d == 'C':
			answer = answer + 'G'
		elif d == 'T':
			answer = answer + 'A'
		elif d == 'A':
			answer = answer + 'U'

	return answer
