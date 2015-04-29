class Bob(object):
  def hey(self, msg):
    if not _is_addressed(msg):
      return 'Fine. Be that way!'
    elif _is_shouted(msg):
      return 'Woah, chill out!'
    elif _is_question(msg):
      return 'Sure.'
    else:
      return 'Whatever.'

def _is_question(msg):
  return msg.endswith('?')

def _is_shouted(msg):
  return msg.upper() == msg and _has_words(msg)

def _is_addressed(msg):
  return msg.strip()

def _has_words(msg):
  return any(c.isalpha() for c in msg)
