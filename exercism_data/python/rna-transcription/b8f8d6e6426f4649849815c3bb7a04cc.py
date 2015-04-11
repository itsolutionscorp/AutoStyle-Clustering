dna_to_rna_map = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U'
}

class DNA(object):
	def __init__(self, dna_str):
		self.dna_str = dna_str

	def to_rna(self):
		return ''.join([dna_to_rna_map[c] for c in self.dna_str])
