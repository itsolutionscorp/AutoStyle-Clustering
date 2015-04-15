import string

def encode(s):
  s = "".join(s.lower().split())
  t = string.maketrans(string.lowercase,string.lowercase[::-1])
  c = string.translate(s,t)

  # Remove punctuation etc. Keep only letters and digits.
  c = filter(lambda x: x in string.letters+string.digits, c)

  # Group cipher words in order to make word boundary attacks more difficult
  group_size = 5
  grouped_c = " ".join([c[i*group_size:(i+1)*group_size] for i in range(len(c)/group_size + 1)]).strip()

  return grouped_c

def decode(s):
  s = "".join(s.lower().split())
  t = string.maketrans(string.lowercase[::-1],string.lowercase)
  c = string.translate(s,t)

  return c
