class Bob:
  def hey(self, msg):
    if not (msg or '').strip():
      return "Fine. Be that way!"
    elif msg.isupper():
      return 'Woah, chill out!'
    elif msg[-1] == '?':
      return 'Sure.'
    else:
      return "Whatever."
