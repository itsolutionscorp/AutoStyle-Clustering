def to_rna(input):
  pairs = {'G': 'C',
           'C': 'G',
           'T': 'A',
           'A': 'U'}
  if len(input) == 1:
    return pairs[input]
  else:
    result = ""
    for letter in input:
      result += pairs[letter]
    return result
