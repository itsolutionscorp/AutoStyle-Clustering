def word2map(word):
	c_map = {}
	for ch in list(word):
		ch = ch.lower()
		if ch in c_map:
			c_map[ch] = c_map[ch] + 1
		else:
			c_map[ch] = 1
	return c_map

def cmp_map(map1, map2):
	for key in map1:
		if key not in map2:
			return False
		if map1[key] != map2[key]:
			return False
		del(map2[key])
	if len(map2):
		return False
	return True

class Anagram:
	def __init__(self, base):
		self.base = base
		self.b_map = word2map(base)

	def match(self, words):
		out = []
		for word in words:
			if word == self.base:
				continue
			w_map = word2map(word)
			if cmp_map(self.b_map, w_map):
				out.append(word)
		return out
