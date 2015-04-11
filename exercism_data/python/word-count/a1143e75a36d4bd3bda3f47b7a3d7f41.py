from collections import Counter

def word_count(text):
  c = Counter(text.split())
  return dict(c)
