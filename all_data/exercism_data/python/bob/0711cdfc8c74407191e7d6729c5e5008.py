def hey(utterance):
  if utterance and utterance.isupper():
    return 'Whoa, chill out!'
    
  if utterance.endswith('?'):
    return "Sure."
    
  if not utterance.strip():
    return "Fine. Be that way!"
    
  return "Whatever."
