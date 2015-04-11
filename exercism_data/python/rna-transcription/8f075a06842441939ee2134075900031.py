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
		return ''.join(self.transcription[nucleotide] for nucleotide in self.strand)
