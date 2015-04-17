def word_count(words):
  words = words.split()
  counts = {}
  for w in words:
    counts[w] = counts.get(w,0) + 1
  return counts
