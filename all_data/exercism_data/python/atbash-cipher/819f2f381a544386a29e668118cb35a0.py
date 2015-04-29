import string

def encode(s):
  t = string.maketrans(string.letters,2 * string.lowercase[::-1])
  c = string.translate(s,t,string.punctuation + ' ')

  # Group cipher words in order to make word boundary attacks more difficult
  group_size = 5
  grouped_c = " ".join([c[i:i+group_size] for i in range(0,len(c),group_size)])

  return grouped_c

def decode(s):
  t = string.maketrans(string.lowercase[::-1],string.lowercase)
  c = string.translate(s,t,' ')

  return c
