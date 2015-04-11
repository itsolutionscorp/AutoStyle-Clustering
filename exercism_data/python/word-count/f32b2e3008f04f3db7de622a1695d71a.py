import re

def word_count(phrase):
  words = {}

  for word in phrase.split() :
    words[word] = len(re.findall(r'(\b|\s)%s(\b|\s|$)' % re.escape(word), phrase))

  return words
