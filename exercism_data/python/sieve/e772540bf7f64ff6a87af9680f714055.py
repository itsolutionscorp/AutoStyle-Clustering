def sieve(limit):
  candidates = dict.fromkeys(range(2,limit+1), True)

  for current in candidates.keys():
    for k in candidates.keys():
      if candidates[k] == False or k == current:
        continue
      if k % current == 0:
        candidates[k] = False

  return [x for x in candidates if candidates[x] == True]
