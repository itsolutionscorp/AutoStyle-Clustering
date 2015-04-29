import string
def word_count(phrase):
  words = {}
  phrase = phrase.split()
  for i in range(len(phrase)):
    w = phrase[i].translate(string.maketrans("",""), string.punctuation).lower()
    if w in words: words[w] += 1
    elif w.isalpha() or w.isdigit(): words[w] = 1
  return words
