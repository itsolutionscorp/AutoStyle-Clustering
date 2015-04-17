def detect_anagrams(word, phrase):
	# for i in phrase:
	# 	if len(word) == len(i):
	# 		if set(word) == set(i):
	# 			return [i]
	return [x for x in phrase
			if sorted(list(word.lower())) == sorted(list(x.lower()))
			and word.lower() != x.lower()]
	
