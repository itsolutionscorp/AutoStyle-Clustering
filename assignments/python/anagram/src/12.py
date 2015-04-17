def detect_anagrams(str1,lst1):
	s1 = [x for x in str1.lower()]
	s1.sort()
	ret = []
	for s in lst1:
		if s == str1:
			break
		s2 = [x for x in s.lower()]
		s2.sort()
		if s2 == s1:
			ret.append(s)
	return ret
