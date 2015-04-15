class DNA:
	transcription = {
		'G' : 'C',
		'C' : 'G',
		'T' : 'A',
		'A' : 'U',
	}

	def __init__(self, value):
		self.strand = value

	def to_rna(self):
		equivalent = ""
		for nucleotide in self.strand:
			equivalent = equivalent + self.transcription[nucleotide]
		return equivalent
