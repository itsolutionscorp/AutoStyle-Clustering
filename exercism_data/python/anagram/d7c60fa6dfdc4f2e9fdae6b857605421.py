def detect_anagrams(word, list):
	l=[]
	for i in list:
		if(are_anagrams(word, i)):
			l.append(i)
	return l


def are_anagrams(a, b):
	if(a.lower() == b.lower()): return False
	return sorted(a.lower()) == sorted(b.lower())
