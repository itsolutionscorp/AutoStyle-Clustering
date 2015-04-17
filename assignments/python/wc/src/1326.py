from collections import Counter
def word_count(self):
	self = ' '.join(self.split()) #split and rejoin to clear out extra spaces and new lines
	words = self.split(' ') #split into array by space delimiting
	finalcount = Counter(words) #count words
	return finalcount
