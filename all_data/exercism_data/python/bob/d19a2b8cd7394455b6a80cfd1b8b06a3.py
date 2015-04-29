import string

def hey(message):
  if len(message.strip()) == 0:
    return 'Fine. Be that way!'
  elif message == message.upper() and any((c in string.ascii_letters) for c in message):
    return "Whoa, chill out!"
  elif message[-1] == '?':
    return "Sure."
  else:
    return "Whatever."
