#!/usr/bin/env python3

from __future__ import print_function
from sys import argv
from collections import Counter

def word_count(str):
	# split string on every whitespace character (space, tab, newline)
	words = str.split()

	# use a special data structure, a Counter, to count the number of
	# elements in an iterable.
	# See https://docs.python.org/2.7/library/collections.html
	counts = Counter(words)
	return counts

if __name__ == '__main__':
	# run word_count on all arguments
	words = " ".join(argv[1:])
	print(word_count(words))
