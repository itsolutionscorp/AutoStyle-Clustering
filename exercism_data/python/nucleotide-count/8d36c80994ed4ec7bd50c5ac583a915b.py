from collections import Counter

class DNA:
	def __init__(self, s):
		self.cnt = Counter(A=0, T=0, C=0, G=0)
		self.cnt.update(s)
		
	def count(self, n):
		if n not in "ACGTU":
			raise ValueError("%s is not a nucleotide." %n)
		return self.cnt[n]
	
	def nucleotide_counts(self):
		return dict(self.cnt)
