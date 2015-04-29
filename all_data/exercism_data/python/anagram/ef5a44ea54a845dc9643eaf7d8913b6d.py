def detect_anagrams(target, choices):
	result = []
	target = target.lower()
	sorted_target = sorted(target)
	for c in choices:
		lower_c = c.lower()
		if not lower_c == target and sorted(lower_c) == sorted_target:
			result.append(c)
	return result
