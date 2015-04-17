def detect_anagrams(master, candidates):
  # get all possible permutations of master
  # note this isn't particularly efficient - we could filter
  # out potential matches first
  from itertools import permutations
  all_perms = [''.join(p) for p in permutations(master.lower())]
  return [cand for cand in candidates if cand.lower() in all_perms and cand.lower() != master.lower()]
