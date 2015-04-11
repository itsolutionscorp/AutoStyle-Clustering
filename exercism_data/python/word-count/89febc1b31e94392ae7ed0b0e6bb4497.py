def word_count(str):
  splits = str.split()
  words = frozenset(splits)
  counts = lambda w: (w, splits.count(w))
  return dict(map(counts, words))
