def detect_anagrams(input, inlist):
  """ detect anagrams from args """

  # ignore case, ignore dupes
  res = []
  for arg in inlist:
    if arg.lower() == input.lower():
      continue
    if decompose(arg) == decompose(input):
      res.append(arg)
  return res

def decompose(input):
  """ initialize input dict to 0 and populate """
  s = list(input.lower())
  s.sort()
  return s
