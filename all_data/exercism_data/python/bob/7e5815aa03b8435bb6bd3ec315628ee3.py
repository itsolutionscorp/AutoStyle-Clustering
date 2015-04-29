def is_empty(msg):
  return not msg or not msg.strip()

def is_shouted(msg):
  return msg.upper() == msg

def is_question(msg):
  return msg.endswith('?')

class Bob:
  def hey(self, msg):
    if is_empty(msg):
      return 'Fine. Be that way!'
    elif is_shouted(msg):
      return 'Woah, chill out!'
    elif is_question(msg):
      return 'Sure.'
    return 'Whatever.'
