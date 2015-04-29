def detect_anagrams(subject, candidates):
	subject_sort = sorted(subject.lower())
	return [candidate for candidate in candidates 
		if 
			sorted(candidate.lower()) == subject_sort
			and candidate.lower() != subject.lower()
		]
