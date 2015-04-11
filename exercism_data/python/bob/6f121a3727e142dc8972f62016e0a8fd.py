import re

# match all digits and non alphanumeric characters
_pattern = re.compile('([\d]|[\W])+', re.U)

def hey(s):
  # empty string
  if s is None or len(s.strip()) == 0:
    return 'Fine. Be that way!'

  # leave only letters behind
  sub = _pattern.sub('', s)
  
  # non empty and all upper (YELLING)
  if sub and all([c.isupper() for c in sub]):
    return 'Whoa, chill out!'
    
  # is a question
  if s[-1] == '?':
    return 'Sure.'

  # catch all
  return 'Whatever.'
