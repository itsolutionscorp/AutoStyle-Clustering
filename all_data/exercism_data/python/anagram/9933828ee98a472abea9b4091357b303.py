#!/usr/bin/python -tt

def detect_anagrams(inStr,lst):
	
	def check_anagram(mstr,cand):
		if len(mstr) != len(cand):
		#If strings have different lenght, return False
			return False
		if mstr == cand:
		#In strings are identical, return False
			return False
		for c in mstr:
		#For each character, if it is in cand: remove it, otherwise return False
			if c not in cand:
				return False
			else:
				cand = cand.replace(c,'',1)
		return True
	
	out=[]
	for cand in lst:
	#Check each candidate in the list for being an anagram
		if check_anagram(inStr.lower(),cand.lower()):
			out.append(cand)
	
	return out
