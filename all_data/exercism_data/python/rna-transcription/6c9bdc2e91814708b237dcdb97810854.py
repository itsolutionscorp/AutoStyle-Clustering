class DNA:
	def __init__(self, seq):
		self.seq = seq
	def to_rna(self):
		rna = ''
		sequence = list(self.seq)
		for letter in sequence:
			if letter == 'A':
				rna += 'U'
			elif letter == 'C':
				rna += 'G'
			elif letter == 'G':
				rna += 'C'
			elif letter == 'T':
				rna += 'A'
			else:
				'Not valid'
		return rna			
