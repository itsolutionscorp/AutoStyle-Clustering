import string

class Phone:
	def __init__(self, nbr):
		nbr = nbr.translate(None, string.punctuation+string.whitespace)
		if len(nbr) == 11 and nbr[0] == '1':
			nbr = nbr[1:]
		elif len(nbr) != 10:
			nbr = "0000000000"
		self.number = nbr
		
	def pretty(self):
		return '({code}) {central}-{nbr}'.format(code=self.number[:3], 
			central=self.number[3:6], nbr=self.number[6:])
		
	def area_code(self):
		return self.number[:3]
