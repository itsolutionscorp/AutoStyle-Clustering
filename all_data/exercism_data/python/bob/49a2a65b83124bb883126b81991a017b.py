#!/usr/bin/python

def hey(input):
  # Strip input string of extra whitespace
  input = input.strip()

  # Input is nothing
  if not input:
    return 'Fine. Be that way!'
  # Input is shouting if it is completely upper case
  elif input.isupper():
    return 'Whoa, chill out!'
  # Input is a question
  elif input.endswith('?'):
    return 'Sure.'
  # Default response for other cases
  else:
    return 'Whatever.'
