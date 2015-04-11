class DNA:
	def __init__(self, dna):
		self.dna = dna 

	def hamming_distance(self, mutation):
		distance, i, j = 0, 0, 0 

		for letter in self.dna:
			if j == len(mutation):
				break
			elif self.dna[i] != mutation[j]:
				distance +=1
			i += 1
			j += 1
		return distance
