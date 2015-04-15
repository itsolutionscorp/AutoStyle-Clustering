# -*- coding: utf-8 -*-

import string

LOWERCASE_CHARACTERS = string.lowercase + "äëïöü"
UPPERCASE_CHARACTERS = string.uppercase + "ÄËÏÖÜ"

def hey(what):
  if len(what.strip()) == 0:
    return "Fine. Be that way!"

  upper_count = 0
  lower_count = 0
  last_char = ''

  for c in what.strip():
    last_char = c.encode('utf-8')
    if last_char in LOWERCASE_CHARACTERS: lower_count += 1
    if last_char in UPPERCASE_CHARACTERS: upper_count += 1

  if lower_count == 0 and upper_count > 0: return "Whoa, chill out!"
  if last_char == "?": return "Sure."

  return "Whatever."
