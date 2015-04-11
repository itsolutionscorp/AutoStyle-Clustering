class Bob:
  def hey(self, msg):
    if not (msg or '').strip(): return "Fine. Be that way!"
    if len([c for c in msg if c.islower()]) is 0: return 'Woah, chill out!'
    if msg[-1] == '?': return 'Sure.'
    return "Whatever."
