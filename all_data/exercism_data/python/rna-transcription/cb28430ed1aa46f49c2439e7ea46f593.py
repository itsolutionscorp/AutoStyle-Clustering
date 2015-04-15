def to_rna(word):
  lists = list(word)
  words = ''
  for ch in lists:
    if 'G' == ch:
      words += 'C'
    elif 'C' == ch:
      words += 'G'
    elif 'T' == ch:
      words += 'A'
    elif 'A' == ch:
      words += 'U'

  return words
