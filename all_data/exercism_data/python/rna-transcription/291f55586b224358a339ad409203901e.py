def transcribe(n):

	if 'g' in n.lower():

		return 'C'

	if 'c' in n.lower():

		return 'G'

	if 't' in n.lower():

		return 'A'

	if 'a' in n.lower():

		return 'U'


def to_rna(dna):

	return ''.join(map(transcribe,dna))
    
