'''
Compute the scrabble score for a word
'''

def scrabbledict():	# create a dictionary of scores for each letter
	old_dict = {
	    1: "aeioulnrst",
	    2: "dg",
	    3: "bcmp",
	    4: "fhvwy",
	    5: "k",
	    8: "jx",
	    10: "qz",
	}
	newdict = {}
	for newkeys,newval in zip(old_dict.values(),old_dict.keys()):
		for newkey in newkeys:
			newdict[newkey] = newval
	return newdict

def score(word):
	newdict = scrabbledict()
	return sum([newdict[ch] for ch in word.lower() if ch.isalpha() ])
