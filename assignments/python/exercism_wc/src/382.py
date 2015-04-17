from __future__ import print_function
from sys import argv
from collections import Counter
def word_count(str):
	words = str.split()
	counts = Counter(words)
	return counts
if __name__ == '__main__':
	words = " ".join(argv[1:])
	print(word_count(words))
