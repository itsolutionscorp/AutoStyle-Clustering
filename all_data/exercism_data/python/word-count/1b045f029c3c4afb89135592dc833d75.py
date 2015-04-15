#!/usr/bin/env python3

from collections import Counter

def word_count(str):
	# split string on every whitespace character (space, tab, newline)
	words = str.split()

	# use a special data structure, a Counter, to count the number of
	# elements in an iterable.
	# This is basically the same as
	# for w in words:
	#   if not w in counter:
	#     counter[w] = 1
	#   else:
	#     counter[w] += 1
	counts = Counter(words)
	return counts

if __name__ == '__main__':
	word_count('Lorem Ipsum dolor sit amet.')
