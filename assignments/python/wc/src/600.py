class word_count(object):
	'''This is a Bob class that does 3 things'''
	def __init__(self, text):
		self.text = text.split()
		list_of_words = {}
		for i in self.text:
			if i in list_of_words:
				list_of_words[i] += 1
			else:
				list_of_words[i] = 1
		print list_of_words
		del list_of_words
