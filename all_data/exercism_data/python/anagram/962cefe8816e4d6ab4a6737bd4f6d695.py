def detect_anagrams(x, l):
	return [c 
		for c in l 
			if to_letter(c) == to_letter(x)
			if c.lower() != x.lower()]

def to_letter(w):
	return sorted(w.lower())
