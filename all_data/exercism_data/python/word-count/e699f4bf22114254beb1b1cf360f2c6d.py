def word_count(text):
  words = text.split()
  counts = {}
  for word in words:
    counts.setdefault(word, 0)
    counts[word] += 1
  return counts
