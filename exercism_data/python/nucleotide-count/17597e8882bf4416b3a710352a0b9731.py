import re

class DNA(object):
	def __init__(self, dna):
		self.dna = dna
		self.nucleotides = "ACGT"
		self.counts = {}
		for nucleotide in self.nucleotides:
			self.counts[nucleotide] = 0

	def nucleotide_counts(self):
		for nucleotide in self.dna:
			self.counts[nucleotide] += 1
		return self.counts 
	
	def count(self, nucleotide):	

		valid = re.compile('^[ACTGU][ACTGU]*$')
		result = re.match(valid, nucleotide)
		if not result:
			raise ValueError, ("%s is not a nucleotide." % nucleotide)

		else:
			dna = list(self.dna)
			return dna.count(nucleotide) if nucleotide != 'U' else 0 
