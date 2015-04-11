class Anagram(object):
  def __init__(self,word):
    self.word = word

  def decompose(self,word):
    out = {}
    for c in word:
      if c in out:
	out[c] += 1
      else:
	out[c] = 1
    return out

  def match(self,words_list):
    out = []
    for w in words_list:
      if w != self.word:
	if self.decompose(self.word.lower()) == self.decompose(w.lower()):
	  out.append(w)
    return out


if __name__ == "__main__":
  Anagram("").decompose("test")
  print Anagram("bob").match("bbo bbc".split())
