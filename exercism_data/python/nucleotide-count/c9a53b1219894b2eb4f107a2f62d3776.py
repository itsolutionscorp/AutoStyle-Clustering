import collections

def nucleotide_counts(sequence):
	result = {'A': 0, 'C': 0, 'T': 0, 'G': 0}
	result.update(collections.Counter(sequence))
	return result

def count(sequence, nucleid):
	if not nucleid in ('A', 'C', 'T', 'G', 'U'):
		raise ValueError('Not a valid Nucleid.')

	return nucleotide_counts(sequence).get(nucleid, 0)
