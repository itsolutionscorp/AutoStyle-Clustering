from collections import Counter
def word_count(phrase):
  word_map = Counter()
  for word in phrase.split():
    word_map[word] += 1
  return word_map
