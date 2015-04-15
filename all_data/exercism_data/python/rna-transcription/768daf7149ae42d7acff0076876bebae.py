def to_rna(DNA):
	RNA = ''
	for i in range(len(DNA)):
		letter = DNA[i]
		if letter is 'C':
			RNA += 'G'
		elif letter is 'G':
			RNA += 'C'
		elif letter is 'A':
			RNA += 'U'
		elif letter is 'T':
			RNA += 'A'

	return RNA
