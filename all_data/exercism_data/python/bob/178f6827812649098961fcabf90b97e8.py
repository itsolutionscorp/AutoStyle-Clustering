import re

def hey(input):
  if re.match('^\s*$', input):
    # Zero or more whitespace characters constitute saying nothing
    return 'Fine. Be that way!'
  elif input.upper() == input and input.lower() != input:
    # Using all caps is yelling.
    # Checking input.lower() != input prevents input with no case (numbers, punctuation) from being interpreted as yelling
    return 'Whoa, chill out!'
  elif input.endswith('?'):
    # Anything which is not yelling and ends in a question mark is a question
    return 'Sure.'
  # Default response to everything else
  return 'Whatever.'
