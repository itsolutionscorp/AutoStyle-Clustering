class Anagram:
	def __init__(self, word):
		self.word = word
		
	def match(self, potentials):
		goodAnagrams = []
		
		wordSet = sorted(self.word.lower())
		
		for word in potentials:
			if word == self.word:
				continue
				
			potentialWordSet = sorted(word.lower())
			
			if (wordSet == potentialWordSet):
				goodAnagrams.append(word)
				
		return goodAnagrams
