import string

def hey(what):
  content = what.strip()

  if (len(content) == 0):
    return 'Fine. Be that way!'

  # If there is at least one alphabetic character, than we need to check the case of the contents.
  transTable = str.maketrans(dict.fromkeys(string.digits + string.punctuation + string.whitespace))
  stripped = content.translate(transTable)

  if (len(stripped) > 0):
    # This means that we have at least one alphabetical character
    if (content == content.upper()):
      return 'Whoa, chill out!'

  # Here, we know that the contents are not all upper case (i.e. non-shouting)
  if (content.endswith('?')):
    return 'Sure.'

  return 'Whatever.'
