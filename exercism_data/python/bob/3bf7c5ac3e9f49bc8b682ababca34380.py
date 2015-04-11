import regex


def hey(text):
  if text.isupper():
    return 'Whoa, chill out!'
  elif text.endswith("?"):
    return 'Sure.'
  elif len(text.strip()) == 0:
    return 'Fine. Be that way!'
  else:
    return 'Whatever.'
