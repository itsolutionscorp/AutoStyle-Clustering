def _normalize_word(word):
	l = list(word.lower())
	l.sort()
	return "".join(l)

class Anagram(object):
	def __init__(self, word):
		self._word = word
		self._normalized_word = _normalize_word(word)

	def match(self, words):
		def predicate(w):
			return w != self._word and _normalize_word(w) == self._normalized_word
		return filter(predicate, words)
