class Anagram(object):
	def __init__(self, word):
		self.word = word
		self.w_sort = self.word_sort(self.word)
	
	def word_sort(self, w):
		wlist = list(w.lower()); wlist.sort()
		return wlist
	
	def match(self, alist):
		matched = []
		for a in alist:
			if len(a) != len(self.word) or a == self.word:
				continue
			a_sort = self.word_sort(a)
			if a_sort == self.w_sort:
				matched.append(a)
		return matched
