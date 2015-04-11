def detect_anagrams(master, candidates):
  srt_master = sorted(master.lower())
  return [cand for cand in candidates if 
sorted(cand.lower()) == srt_master and cand.lower() != master.lower()]
