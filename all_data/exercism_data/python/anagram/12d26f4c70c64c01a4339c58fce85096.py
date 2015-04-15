class Anagram(object):
	def __init__ (self, word):
		self.word = word.lower()
		
		anagrams = {}
		for letter in self.word:
			if anagrams.get(letter) is not None:
				anagrams[letter] +=1
			else:
				anagrams[letter] = 1
		self.anagrams = anagrams


	def match(self, candidates):
		matches = []
		for candidate in candidates:
			if candidate.lower() == self.word or len(candidate) != len(self.word):
				continue	
			else:
				letter_count = {}
				for letter in candidate.lower():
					if letter_count.get(letter) is not None:
						letter_count[letter] += 1
					else:
						letter_count[letter] = 1
				if letter_count == self.anagrams:
					matches.append(candidate)
		return matches
