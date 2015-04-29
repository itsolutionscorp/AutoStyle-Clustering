def word_count(word):
  words = word.strip().split()
  index = {}
  keys = index.keys()
  for word in words:
    if word not in keys:
      index[word] = 1
    else:
      index[word] += 1
  return index
