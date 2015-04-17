def detect_anagrams(master, candidates):
  from collections import Counter
  cnt_master = Counter(master.lower())
  return [cand for cand in candidates if 
Counter(cand.lower()) == cnt_master and cand.lower() != master.lower()]
