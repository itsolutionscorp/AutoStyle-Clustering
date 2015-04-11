from string import ascii_lowercase

tr = str.maketrans(ascii_lowercase, ''.join(reversed(ascii_lowercase)))

def prune(s):
  '''converts everything to lower-case and only retains a-z/digits'''
  return ''.join(ch for ch in s.lower() if ch in ascii_lowercase or ch.isdigit())

def spaceout(s):
  i = 1
  s = list(s) # because we want to use pop
  while s:
    if i % 6 == 0:
      yield ' '
    else:
      yield s.pop(0)
    i += 1

def decode(s):
  return prune(s).translate(tr)

def encode(s):
  return ''.join(spaceout(prune(s).translate(tr)))
