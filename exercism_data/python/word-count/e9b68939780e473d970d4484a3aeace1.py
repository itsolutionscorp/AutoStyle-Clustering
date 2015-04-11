import re

def word_count(s):
  s = re.sub("[^\w]", " ", s.lower())
  words = {}
  for word in s.split():
    if words.has_key(word):
      words[word] += 1
    else:
      words[word] = 1 
  return words
