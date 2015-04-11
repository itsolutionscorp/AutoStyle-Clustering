def sieve(limit):
  candidates = dict.fromkeys(range(2,limit+1), None)

  for current in candidates.keys():
    for k in candidates.keys():
      if candidates[current] == None:
        candidates[current] = 'Prime'
      else:
        if candidates[k] == None:
          if k % current == 0:
            candidates[k] = 'Composite'

  return [x for x in candidates if candidates[x] == 'Prime']
