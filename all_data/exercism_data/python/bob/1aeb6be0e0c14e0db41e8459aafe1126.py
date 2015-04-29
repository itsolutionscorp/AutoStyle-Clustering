#
# Skeleton file for the Python "Bob" exercise.
#

import string

def hey(what):

    # Remove surrounding whitespace
    what = what.strip()

    used_letters = set(string.ascii_letters) & set(what)
    has_letters = len(used_letters) > 0

    if what == "": # Silence
      return 'Fine. Be that way!'
    elif what == what.upper() and has_letters: # Shouting (need letters)
      return 'Whoa, chill out!'
    elif what.endswith('?'): # Quetion
      return 'Sure.'
    else:
      return "Whatever."
