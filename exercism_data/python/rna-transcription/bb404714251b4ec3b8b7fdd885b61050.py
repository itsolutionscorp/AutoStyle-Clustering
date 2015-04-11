def to_rna(strand):
	transcription = None
	rna = []
	result = ''
	compliment = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U',
	}

	if len(strand) > 1:
		for item in strand:
			rna.append(item)
		for item in tuple(rna):
			result = result + compliment[item]
		transcription = result
	else:
		transcription = compliment[strand]

	return transcription
