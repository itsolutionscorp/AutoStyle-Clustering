from collections import Counter

def detect_anagrams(target, words):
	target, lower_words = target.lower(), [w.lower() for w in words]
	return [words[i] for i, w in enumerate(lower_words) if (
					Counter(target) == Counter(w)
					and target != w)]
