def to_rna(txt):
  # `G` -> `C`
  # `C` -> `G`
  # `T` -> `A`
  # `A` -> `U`
  def translate(c):
    if c == 'G':
      return 'C'
    elif c == 'C':
      return 'G'
    elif c == 'T':
      return 'A'
    elif c == 'A':
      return 'U'
    else:
      return c

  return "". join([translate(c) for c in txt])
