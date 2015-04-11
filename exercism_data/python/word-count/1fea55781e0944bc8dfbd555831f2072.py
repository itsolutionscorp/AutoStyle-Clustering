from collections import defaultdict as dd

def word_count(s):
  counts = dd(int)
  for word in s.split():
    # Lowercase and strip punctuation
    normalized = ''.join(c.lower() for c in word if c.isalnum())
    # If our token was all punctuation, don't store anything.
    if not normalized:
      continue
    counts[normalized] += 1
  return counts
