# Kevin Barbour
# exercism.io - word-count

import string

def word_count(phrase):
  words = {}
  # not entirely clear on how this string.translate method works
  phrase = phrase.translate(string.maketrans("",""), string.punctuation).lower().split()

  for i in range(len(phrase)):
    w = phrase[i]

    if w in words: words[w] += 1
    elif w.isalnum(): words[w] = 1

  return words
