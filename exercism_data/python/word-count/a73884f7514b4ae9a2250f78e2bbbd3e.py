class phrase(object):
	def __init__(self, phrase):
		self.words = phrase.split()
		
	def word_count(self):
		word_dict = {}
		
		for word in self.words:
			word_input = ''.join(word).strip()
			if word_input:
				word_dict[word_input] = word_dict.get(word_input, 0) + 1
		return word_dict
		
	
