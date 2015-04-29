def sieve(limit):
  candidates = dict.fromkeys(range(2,limit+1), True)

  for current in candidates.keys():
    for k in [x for x in candidates if candidates[x] != False]:
      if k % current == 0 and k != current:
        candidates[k] = False

  return [x for x in candidates if candidates[x] == True]
