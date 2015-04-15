#!/usr/bin/python

import string

def compare(base,test):
	base = string.lower(base)
	test = string.lower(test)
	if (base == test):
		return 0
	base_list = list(base)
	test_list = list(test)
	base_list.sort()
	test_list.sort()
	if (base_list == test_list):
		return 1
	else:
		return 0


def detect_anagrams(base_word,test_list):
	len_base = len(base_word)
	anagram_list = []
	for x in test_list:
		len_test = len(x)
		if (len_test != len_base):
			continue
		elif (x == base_word):
			continue
		else:
			if not (compare(x,base_word)):
				continue
			else:
				if (x in anagram_list):
					continue
				else:
					anagram_list.append(x)
	return anagram_list
	





	
