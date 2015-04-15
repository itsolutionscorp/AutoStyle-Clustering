from collections import Counter

def word_count(phrase):
  '''Returns a dict of word counts for each word in the passed phrase.'''
  return Counter(phrase.split())
