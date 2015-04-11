def distance(a, b):
  return sum(1 for (aa,bb) in zip(a,b) if aa != bb)
