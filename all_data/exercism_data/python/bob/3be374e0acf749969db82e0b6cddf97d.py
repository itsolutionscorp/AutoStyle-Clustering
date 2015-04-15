from __future__ import unicode_literals

def hey(msg):
  if not msg.strip():
    return "Fine. Be that way!"

  if msg.isupper():
    return "Whoa, chill out!"

  if msg.strip().endswith("?"):
    return "Sure."

  return "Whatever."
