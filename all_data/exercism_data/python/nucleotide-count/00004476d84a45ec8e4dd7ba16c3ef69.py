from collections import defaultdict
from sets import Set

_dna_nucleotides = Set(['A', 'T', 'C', 'G'])
_nucleotides = _dna_nucleotides.copy()
_nucleotides.add('U')


class DNA(object):
	def __init__(self, strand):
		self.strand = strand

	def count(self, nucleotide):
		if nucleotide not in _nucleotides:
			raise ValueError(str(nucleotide) + ' is not a nucleotide.')
		if nucleotide == 'U':
			return 0
		return self.nucleotide_counts()[nucleotide]

	def nucleotide_counts(self):
		counts = defaultdict(lambda: 0)
		for nucleotide in _dna_nucleotides:
			counts[nucleotide]
		for c in self.strand:
			if c not in _dna_nucleotides:
				raise ValueError(str(nucleotide) + ' is not a dna nucleotide.')
			counts[c] += 1
		return counts
