class DNA(object):
	def __init__(self, sequence):
		self.sequence = sequence
	
	def count(self, n):
		nucleotides = "ACTGU"
		if n not in nucleotides:
			raise ValueError("%s is not a nucleotide." % n)
		return self.sequence.count(n)
	
	def nucleotide_counts(self):
		return { s: self.count(s) for s in 'ACTG' }
