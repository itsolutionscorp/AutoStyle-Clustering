def word_count(message):
  words = message.split()
  counts = {}
  for word in words:
    counts[word] = counts[word]+1 if word in counts else 1
  return counts
