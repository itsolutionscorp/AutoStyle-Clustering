
def hey(statement):
    
  if (statement.endswith('?') and not statement.isupper()):
    return 'Sure.'

  elif (statement.isupper()):
	return 'Whoa, chill out!'	

  elif (statement.strip() == ''):
	return 'Fine. Be that way!'

  else:
    return 'Whatever.'
