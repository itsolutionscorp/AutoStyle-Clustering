def to_rna(rna):
  trans = ""
  for c in rna:
    if c == "C":
      trans += "G"
    elif c == "G":
      trans += "C"
    elif c == "T":
      trans += "A"
    else:
      trans += "U"    
  return trans
