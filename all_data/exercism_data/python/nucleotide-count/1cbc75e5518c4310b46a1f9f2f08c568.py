from collections import Counter


class DNA(object):
	def __init__(self, strand):
		self.strand = strand

	def count(self, nucleotide):
		if nucleotide not in 'ACGTU':
			raise ValueError('{} is not a nucleotide.'.format(nucleotide))

		counter = self.nucleotide_counts()
		return counter[nucleotide]

	def nucleotide_counts(self):
		counter = Counter(A=0, C=0, G=0, T=0)
		counter.update(self.strand)
		return counter
