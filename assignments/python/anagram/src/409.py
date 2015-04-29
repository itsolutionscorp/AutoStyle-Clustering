def detect_anagrams(cand, string):
	word=[]
	for i in string:
		if sorted(i.lower())==sorted(cand.lower()) and i.lower() != cand.lower():
			word.append(i)
	return word
