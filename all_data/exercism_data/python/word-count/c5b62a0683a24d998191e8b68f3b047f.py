#!/usr/bin/env python3

from collections import Counter

def word_count(str):
	words = str.split()
	counts = Counter(words)
	return counts
