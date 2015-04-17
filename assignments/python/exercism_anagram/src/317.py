from collections import Counter
def detect_anagrams(masterword, complist):
	resultlist = []
	for word in complist:
		wordtest = word.lower()
		masterwordtest = masterword.lower()
		
		if wordtest != masterwordtest and Counter(wordtest) == Counter(masterwordtest):
			resultlist.append(word)
	return resultlist
