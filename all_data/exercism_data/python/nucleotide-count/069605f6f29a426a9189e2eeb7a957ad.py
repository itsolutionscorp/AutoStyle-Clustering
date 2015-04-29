class DNA(object):	
	
	def __init__(self, sequence):
		self.sequence = sequence

	def nucleotide_counts(self):
		nucleotide_count = {
			'A': 0, 
			'C': 0, 
			'G': 0, 
			'T': 0,
			}

		for i in self.sequence:
			if i == "A":
				nucleotide_count[i] += 1
			elif i == "C":
				nucleotide_count[i] += 1
			elif i == "G":
				nucleotide_count[i] += 1
			elif i == "T":
				nucleotide_count[i] += 1
			else:
				raise ValueError

		return nucleotide_count

	def count(self, nucleotide):
		errmsg = "%s is not a nucleotide." % nucleotide
		Atide = 0
		Ctide = 0
		Gtide = 0
		Ttide = 0
		Utide = 0

		for i in self.sequence:
			if i == "A":
				Atide += 1
			elif i == "C":
				Ctide += 1
			elif i == "G":
				Gtide += 1
			elif i == "T":
				Ttide +=1 
			elif i == "U":
				Utide += 1
		
		if nucleotide == "A":
			return Atide
		elif nucleotide == "C":
			return Ctide
		elif nucleotide == "G":
			return Gtide
		elif nucleotide == "T":
			return Ttide
		elif nucleotide == "U":
			return Utide
		else:
			raise ValueError(errmsg)
