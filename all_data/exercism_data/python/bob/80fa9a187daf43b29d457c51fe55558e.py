#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
    what = what.strip()

    if re.compile(r"[!123,\w %^*@#$(*^]+[?!.]?$", re.UNICODE).match(what) and what == what.upper() and re.compile(r"[A-Z]", re.UNICODE).search(what):
      return 'Whoa, chill out!'
    elif re.compile(r".+\?$").match(what):
      return 'Sure.'
    elif what == '':
      return 'Fine. Be that way!'
    else:
      return 'Whatever.'

    return
