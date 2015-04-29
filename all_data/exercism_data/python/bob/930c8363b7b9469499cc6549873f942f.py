def hey(message):
  message = message.strip()  #padding whitespace would confuse the message
  if not message:  #calling Bob without saying anything
    return('Fine. Be that way!')
  elif (message.upper()==message) and (message.lower()!=message):
    #yelling based on all caps and presence of letters. 'I?' is yelling at Bob
    return('Whoa, chill out!')
  elif message.endswith('?'):
    return('Sure.')
  else:
    return('Whatever.')
