def to_rna(n):
  new = ''
  for letter in n:
    if letter == 'G':
      new += 'C'
    elif letter == 'C':
      new += 'G'
    elif letter == 'A':
      new += 'U'
    elif letter =='T':
      new += 'A'
    else:
      continue
  return new

print to_rna('ACGTGGTCTTAA')
