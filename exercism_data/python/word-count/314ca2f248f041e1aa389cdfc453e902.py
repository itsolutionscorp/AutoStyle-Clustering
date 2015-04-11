from collections import Counter
import string

def word_count(s):
  s = ''.join([c for c in s if c.isalnum() or c.isspace()])
  return dict(Counter(s.lower().split()))
