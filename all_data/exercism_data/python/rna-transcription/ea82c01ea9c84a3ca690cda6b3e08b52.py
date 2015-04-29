def to_rna(st):
  res = ''
  for letter in st:
    if letter == "A":
      res += 'U'
    elif letter == "T":
      res += "A"
    elif letter == "C":
      res += "G"
    else: 
      res += "C"

  return res
