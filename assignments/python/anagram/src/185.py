from collections import Counter
# determines which if any elements of list candidates are anagrams of str base
def detect_anagrams(base, candidates):
	target=Counter(base.lower())
	answer=[]
	for word in candidates:
		if Counter(word.lower())==target and base.lower()!=word.lower():
			answer.append(word)
	return answer
