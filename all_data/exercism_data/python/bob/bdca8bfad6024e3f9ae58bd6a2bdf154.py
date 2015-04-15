def hey(message):
  message = message.strip()  #padding whitespace would confuse the message
  if len(message)==0:  #calling Bob without saying anything
    return('Fine. Be that way!')
  elif (message.upper()==message) and (message.lower()!=message):
    #yelling based on all caps and presence of letters. 'I?' is yelling at Bob
    return('Whoa, chill out!')
  elif message[-1]=='?':
    #question if the last non-whitespace character is a '?'
    return('Sure.')
  else:
    return('Whatever.')
