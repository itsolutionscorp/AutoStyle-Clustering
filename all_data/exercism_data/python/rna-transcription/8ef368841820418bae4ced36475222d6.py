def to_rna(DNA):
	RNA = list(DNA)
	for i in range(len(RNA)):
		if RNA[i] == 'G':
			RNA[i] = 'C'

		elif RNA[i] == 'C':
			RNA[i] = 'G'

		elif RNA[i] == 'T':
			RNA[i] = 'A'

		elif RNA[i] == 'A':
			RNA[i] = 'U'


	return "".join(RNA)
