from collections import Counter

def detect_anagrams(target, sentence):
	return filter(lambda t: isAnagram(t.lower(),target.lower()), sentence)

def isAnagram(test, target):
	""" Using Counter to compare 
	    2 Words: word identity isn't an anagram
	"""
	return not test == target and Counter(test) == Counter(target)
