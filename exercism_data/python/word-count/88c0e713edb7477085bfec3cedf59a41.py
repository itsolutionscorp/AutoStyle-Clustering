import re

def word_count(phrase):
  words = phrase.split()
  return { item: words.count(item) for item in words }


  #words = {}

  #for word in phrase.split() :
  #  words[word] = len(re.findall(r'(\b|\s)%s(\b|\s|$)' % re.escape(word), phrase))

  #return words
