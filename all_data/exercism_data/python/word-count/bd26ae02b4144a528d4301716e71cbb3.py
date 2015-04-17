# Kevin Barbour
# exercism.io - word-count

import string

def word_count(phrase):
  words = {}
  phrase = phrase.split()
  for i in range(len(phrase)):
    # not entirely clear on how this string.translate method works
    w = phrase[i].translate(string.maketrans("",""), string.punctuation).lower()
    if w in words: words[w] += 1
    elif w.isalnum(): words[w] = 1
  return words