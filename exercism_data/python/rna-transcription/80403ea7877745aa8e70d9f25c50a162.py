def to_rna(code):
  res = ''
  correspondence = {'G':'C','C':'G','T':'A','A':'U'}
  #* `G` -> `C`
  #* `C` -> `G`
  #* `T` -> `A`
  #* `A` -> `U`
  for i in code:
    res = res+correspondence[i]
  return res
