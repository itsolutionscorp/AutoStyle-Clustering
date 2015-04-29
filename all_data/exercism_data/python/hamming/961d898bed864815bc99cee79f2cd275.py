def distance(a,b):
  match = [v==b[i] for i,v in enumerate(a)]
  return match.count(False)
