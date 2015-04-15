def distance(s1, s2):
	mutations = [(i, c) for i, c in enumerate(s2) \
							if s1[i] != c]
	return len(mutations)
