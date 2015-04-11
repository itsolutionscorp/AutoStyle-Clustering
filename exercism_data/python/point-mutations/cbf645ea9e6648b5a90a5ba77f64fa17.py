class DNA:
	def __init__(self, strand):
		self.strand = strand 

	def hamming_distance(self, mutation):
		distance, i, j = 0, 0, 0 

		for letter in self.strand:
			if j == len(mutation):
				break
			elif self.strand[i] != mutation[j]:
				distance +=1
			i += 1
			j += 1
		return distance
