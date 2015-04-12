def hey(input):
  # Work with unicode and handle non-string input
  input = unicode(input)

  if input.isspace() or not input:
    # Empty string or only whitespace characters constitute saying nothing
    return 'Fine. Be that way!'
  elif input.isupper():
    # Using all caps is yelling.
    # isupper() is False for input with no case (numbers, punctuation) and will prevent it from being interpreted as yelling
    return 'Whoa, chill out!'
  elif input.endswith('?'):
    # Anything which is not yelling and ends in a question mark is a question
    return 'Sure.'
  else:
    # Default response to everything else
    return 'Whatever.'