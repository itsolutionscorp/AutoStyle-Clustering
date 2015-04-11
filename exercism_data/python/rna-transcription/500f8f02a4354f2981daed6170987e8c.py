
def to_rna(string):

	def transcribe(letter):

		if letter == 'G': return 'C'
		if letter == 'C': return 'G'
		if letter == 'T': return 'A'
		if letter == 'A': return 'U'

	return ''.join(transcribe(letter) for letter in string)
