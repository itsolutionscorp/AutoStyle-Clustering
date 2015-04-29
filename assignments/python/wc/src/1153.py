from string import split
def word_count(str):
  words = {}
  for w in split(str):
    if w in words:  words[w] += 1
    else:           words[w] = 1
  return words
