class Bob(object):

  def hey(self, msg):
      if   is_silence(msg):  return "Fine. Be that way!"
      elif is_shout(msg):    return "Woah, chill out!"
      elif is_question(msg): return "Sure."
      else:                  return "Whatever."

def is_shout(msg):    return msg.isupper()
def is_silence(msg):  return msg is None or not msg.strip()
def is_question(msg): return msg.endswith("?")
