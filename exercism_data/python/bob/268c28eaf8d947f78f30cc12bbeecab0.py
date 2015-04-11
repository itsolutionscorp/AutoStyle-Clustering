# -*- coding: utf-8 -*-

def hey(input):

  if input.strip() == '':
    response = 'Fine. Be that way!'
  elif input.isupper():
    response = 'Woah, chill out!'
  elif input[-1] == '?':
    response = 'Sure.'
  else:
    response = 'Whatever.'

  return response
