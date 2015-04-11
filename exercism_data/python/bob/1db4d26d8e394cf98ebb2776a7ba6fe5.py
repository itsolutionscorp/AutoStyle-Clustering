class Bob:

  def hey(self, msg):
    if self.is_silence(msg):    return "Fine. Be that way!"
    elif self.is_shout(msg):    return "Woah, chill out!"
    elif self.is_question(msg): return "Sure."
    else:                       return "Whatever."

  def is_silence(self, msg):  return msg is None or msg.strip() == ''
  def is_shout(self, msg):    return msg.isupper()
  def is_question(self, msg): return msg.endswith("?")
