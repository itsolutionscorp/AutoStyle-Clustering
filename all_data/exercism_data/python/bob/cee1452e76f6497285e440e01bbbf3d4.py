# -*- coding: utf-8 -*-

def hey(n):
  if n.isupper():
     return 'Whoa, chill out!'
  elif n.endswith('?'):
     return 'Sure.'
  elif not n.strip():
     return 'Fine. Be that way!'
  else:
     return 'Whatever.'
