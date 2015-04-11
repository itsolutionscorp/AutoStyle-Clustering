def hey(to_bob):
  if len(to_bob.strip()) == 0:
    # Nothing - Only whitespace
    return 'Fine. Be that way!'
  elif to_bob == to_bob.upper() and to_bob != to_bob.lower():
    # Shouting - There are cased characters, and they're all uppercase
    return 'Whoa, chill out!'
  elif to_bob[-1] == '?':
    # A question that is not shouting
    return 'Sure.'
  else:
    return 'Whatever.'
