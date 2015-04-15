class DNA:
	def __init__(self, str):
		nucls = { 'A': 0, 'T': 0, 'C': 0, 'G': 0, 'U': 0 }
		for ch in list(str):
			if ch in nucls:
				nucls[ch] = nucls[ch] + 1
		self.nucls = nucls

	def nucleotide_counts(self):
		out = self.nucls.copy()
		if out['U'] == 0:
			del(out['U'])
		return out

	def count(self, ch):
		if ch not in self.nucls:
			raise ValueError("{0} is not a nucleotide.".format(ch))
		return self.nucls[ch]
