def sanitize(s):
  if s is None:
    return ''
  else:
    return s.strip()

def is_question(s):
  return s.endswith('?')

def is_shouting(s):
  return s.isupper()

def is_silent(s):
  return s == ''

class Bob:
  def hey(self, query):
    san_query = sanitize(query)
    if is_silent(san_query):
      return "Fine. Be that way!"
    elif is_shouting(san_query):
      return "Woah, chill out!"
    elif is_question(san_query):
      return "Sure."
    else:
      return "Whatever."
