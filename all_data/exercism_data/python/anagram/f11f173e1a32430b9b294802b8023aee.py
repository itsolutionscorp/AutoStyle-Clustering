class Anagram:

	def __init__(self, word):
		self.word = word
		self.word_sorted = self.sortchars(word.lower())
		self.anagrams = []


	def match(self, wordlist):
		for w in wordlist:
			w_sorted = self.sortchars(w.lower())
			if w_sorted==self.word_sorted and not w.lower()==self.word.lower():
				self.anagrams.append(w)

		return self.anagrams


	def sortchars(self, word):
		w = [x for x in word]
		w.sort()
		return "".join(w)
