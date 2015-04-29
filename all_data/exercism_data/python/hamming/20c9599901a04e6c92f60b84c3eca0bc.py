class HammingDistanceError(Exception):
	"""Exception raised when calculating Hamming Distance """
	def __init__(self, value1, value2):
		self.value1 = value1
		self.value2 = value2

def distance(dna1, dna2):
	try:
		if len(dna1) is not len(dna2):
			raise HammingDistanceError(dna1, dna2)
		else:
			distance = 0
			for a ,b  in zip(dna1, dna2):
				distance+= a!=b
			return distance	 	
	except HammingDistanceError as e:
		print "Different Length DNA Strains."
