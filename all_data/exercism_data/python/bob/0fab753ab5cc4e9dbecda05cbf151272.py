import string

def hey(what):
  content = what.strip()
  result = 'Fine. Be that way!'

  if (content):
    # If there is at least one alphabetic character, than we need to check
    # the case of the contents.
    transTable = str.maketrans(dict.fromkeys(string.digits + 
      string.punctuation + string.whitespace))
    stripped = content.translate(transTable)

    if (stripped and stripped == stripped.upper()):
      result = 'Whoa, chill out!'
    elif (content.endswith('?')):
      result = 'Sure.'
    else:
      result = 'Whatever.'

  return result
