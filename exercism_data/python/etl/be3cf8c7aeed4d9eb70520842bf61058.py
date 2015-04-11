def transform(old):

  new = {}
  for p,v in old.iteritems():
    for i in list(v):
      new[i.lower()] = p
  return new
