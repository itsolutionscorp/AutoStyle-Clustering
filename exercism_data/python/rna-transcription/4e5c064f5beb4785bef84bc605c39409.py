def transcriptChar(char):
	if not char.upper() in ('A', 'T', 'G', 'C'):
		return None
	else:
		transcript = {'A':'U', 'T':'A', 'C':'G', 'G':'C'}
		return transcript[char.upper()]


def to_rna(dna):
	rna = ''
	for char in dna:
		rna = rna + transcriptChar(char)
	return rna
