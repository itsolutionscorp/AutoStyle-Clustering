def hey(input):
  if input and input.isupper():
    return 'Whoa, chill out!'
    
  if input.endswith('?'):
    return "Sure."
    
  if not input.strip():
    return "Fine. Be that way!"
    
  return "Whatever."
