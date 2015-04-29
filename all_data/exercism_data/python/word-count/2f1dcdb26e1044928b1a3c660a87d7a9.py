import string

class Phrase:
	def __init__(self, phrase):
		#Remove punctuation
		phrase = phrase.translate(string.maketrans("",""), string.punctuation)
		
		#Strip extra whitespace and make lowercase
		phrase = phrase.strip().lower()
		
		#Split each word into a list
		words = phrase.split()
		
		self.word_counts = {}
		
		for word in words:
			if word in self.word_counts:
				self.word_counts[word] += 1
			else:
				self.word_counts[word] = 1
		
	def word_count(self):
		return self.word_counts
