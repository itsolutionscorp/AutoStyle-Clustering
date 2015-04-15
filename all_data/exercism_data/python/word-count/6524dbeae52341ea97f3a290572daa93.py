# parse by space and tabs and new lines only

class word_count(object):
	'''This is a Bob class that does 3 things'''
	
	def __init__(self, text):
		self.text = text.split()
		# use dict to store text and the number of each appearance
		list_of_words = {}
		
		for i in self.text:
			if i in list_of_words:
				list_of_words[i] += 1
			else:
				list_of_words[i] = 1
		print list_of_words
		# clean up variable
		del list_of_words
