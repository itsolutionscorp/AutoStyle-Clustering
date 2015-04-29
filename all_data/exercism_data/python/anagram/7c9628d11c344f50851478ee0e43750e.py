class Anagram(object):
	def __init__(self, origin):
		self.origin = origin

	def match(self, possmatch):
		origin = self.origin # start word

		actualmatches = []
		for word in possmatch:
			if "".join(sorted(word.lower())) == "".join(sorted(origin.lower())) and word != origin:
				actualmatches.append(word)
		return actualmatches
