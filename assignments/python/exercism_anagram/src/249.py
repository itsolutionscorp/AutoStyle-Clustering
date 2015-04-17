# anagram.py
from collections import Counter
class Anagram:
	def __init__(self,a):
		self.match = lambda l: [x for x in l if (Counter(a.lower()) == Counter(x.lower()) and a.lower() != x.lower())]
