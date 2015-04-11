def word_count(phrase):
  wtable = {}

  for word in phrase.split():
    wtable[word] = wtable.get(word, 0) + 1

  return wtable
