import re
def word_count(phrase):
  words = phrase.split()
  return { item: words.count(item) for item in words }
