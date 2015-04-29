class DNA(object):

	def __init__(self, dna):
		self.dna = dna

	def to_rna(self):

		lista = list(self.dna)

		for x in xrange(len(lista)):
			if lista[x] == 'G':
				lista[x] = 'C'
			elif lista[x] == 'C':
				lista[x] = 'G'
			elif lista[x] == 'T':
				lista[x] = 'A'
			elif lista[x] == 'A':
				lista[x] = 'U'

		transcription = ''.join(lista)

		return transcription
