def word_count(string):

  counts = {}

  for word in string.split():
    counts[word] = (counts[word] if counts.has_key(word) else 0) + 1

  return counts
