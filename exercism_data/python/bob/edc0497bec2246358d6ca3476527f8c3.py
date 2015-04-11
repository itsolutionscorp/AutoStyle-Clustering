def hey(text):
  has_lower = False
  has_upper = False
  is_question = False
  has_number = False
  has_alpha = False

  if(text.endswith("?")):
    is_question = True

  for ch in text:
    if(ch.islower()):
      has_lower = True
      has_alpha = True
    if(ch.isupper()):
      has_upper = True
      has_alpha = True
    if(ch.isnumeric()):
      has_number = True

  #responses
  if(not is_question and (has_lower or (has_number and not has_alpha))):
    return 'Whatever.'
  elif(has_upper and not has_lower):
    return 'Whoa, chill out!'
  elif(is_question and (has_alpha or(not has_alpha and has_number))):
    return 'Sure.'
  elif((has_number and not has_alpha) or (not has_alpha and not has_number)):
    return 'Fine. Be that way!'
