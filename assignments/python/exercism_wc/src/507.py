def word_count(words):
  from collections import Counter
  from itertools import chain
  o = [w.split(' ') for w in words.split('\n')]
  c = Counter(word for word in chain.from_iterable(o) if word)
  return dict(c.items())
