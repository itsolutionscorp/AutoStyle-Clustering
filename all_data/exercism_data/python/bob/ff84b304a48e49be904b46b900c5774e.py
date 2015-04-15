from __future__ import unicode_literals
import string
WHATEVER = "Whatever."
WHOA_CHILL_OUT = "Whoa, chill out!"
SURE = "Sure."
FINE_BE_THAT_WAY = "Fine. Be that way!"


def _stripPunctuation(msg):
  exclude = set(string.punctuation)
  return ''.join(ch for ch in msg if ch not in exclude)

def _isItAQuestion(msg):
  if msg.strip().endswith("?"):
    return True
  return False


def _containsCAPSWord(msg):
  msg = _stripPunctuation(msg)
  ALLOWED_CAP_WORDS = set(["OK", "DMV"])
  words = msg.split()
  for word in words:
    if word in ALLOWED_CAP_WORDS:
      continue
    if word.isupper():
      return True
  return False


def hey(msg):
  if not msg.strip():
    return FINE_BE_THAT_WAY

  if _containsCAPSWord(msg):
    return WHOA_CHILL_OUT

  if _isItAQuestion(msg):
    return SURE

  return WHATEVER
