from collections import Counter

def detect_anagrams(word, candidates):
  wc = Counter(word.lower()).most_common()
  res = []
  for cand in candidates:
    if wc == Counter(cand.lower()).most_common() and word.lower() != cand.lower():
      res.append(cand)
  return res
