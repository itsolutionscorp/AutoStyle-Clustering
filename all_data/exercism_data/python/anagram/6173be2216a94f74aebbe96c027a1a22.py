class Anagram(object):
	def __init__ (self, str):
		self.str = str.lower()
		
		anagrams = {}
		for letter in self.str:
			if anagrams.get(letter) is not None:
				anagrams[letter] +=1
			else:
				anagrams[letter] = 1
		self.anagrams = anagrams


	def match(self, words):
		matches = []
		for word in words:
			if word.lower() == self.str:
				continue	
			else:
				letter_count = {}
				for letter in word.lower():
					if letter_count.get(letter) is not None:
						letter_count[letter] += 1
					else:
						letter_count[letter] = 1
				if letter_count == self.anagrams:
					matches.append(word)
		return matches
