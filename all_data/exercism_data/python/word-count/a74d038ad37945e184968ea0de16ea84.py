# wordcount.py
# Write a program that given a phrase can count the occurrences of each word in that phrase.

class Phrase:
	def __init__(self,s):
		self.w 	= s.split()

	def word_count(self):
		""" Break down words and return dict"""
		d = {}
		for e in self.w:
			e = e.strip(',').strip(':').lower()
			if not len(e):
				continue
			# strip word
			i = 0
			while i<len(e) and (e[i].isalpha() or e[i].isdigit()):
				i+=1
			e = e[:i]

			if e in d.keys():
				d[e] += 1
			else:
				d[e] = 1
		return d
