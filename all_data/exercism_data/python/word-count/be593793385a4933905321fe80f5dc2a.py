from string import split

def word_count(str):
  words = {}

  # For each word in the string, increase its value in
  # the dictionary, or add it if it's not already there.
  for w in split(str):
    if w in words:  words[w] += 1
    else:           words[w] = 1

  return words
