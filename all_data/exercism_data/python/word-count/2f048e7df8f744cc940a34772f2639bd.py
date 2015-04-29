import re

class Phrase:

	def __init__(self, string):
		self.string= re.split("\W", string)

	def word_count(self):
		diction = {}

		for i in range(len(self.string)):
			key = self.string[i].lower()
				#lower() method makes sure that the GO example passes
			if key == "":
				continue
			else:
				try:
					diction[key]
				except KeyError:
					diction[key] = 1
				else:
					val = diction[key]
					val+=1
					diction[key] = val
		return diction

		
