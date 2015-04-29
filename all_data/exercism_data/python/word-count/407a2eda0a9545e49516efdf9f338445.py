from collections import Counter

def word_count(s):
  s = ''.join([c for c in s if c.isalnum() or c.isspace()])
  return dict(Counter(s.lower().split()))
