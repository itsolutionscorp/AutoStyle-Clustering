class DNA(object):
	def __init__(self, sequence):
		self.sequence = sequence
	
	def count(self, n):
		if n not in "ACTGU":
			raise ValueError("%s is not a nucleotide." % n)
		return self.sequence.count(n)
	
	def nucleotide_counts(self):
		return { s: self.count(s) for s in 'ACTG' }
