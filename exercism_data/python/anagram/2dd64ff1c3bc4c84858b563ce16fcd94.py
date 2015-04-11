def detect_anagrams(word, group):
	return [item for item in group
			if item.lower() != word.lower()
			and sorted(list(item.lower())) == sorted(list(word.lower()))
			]
