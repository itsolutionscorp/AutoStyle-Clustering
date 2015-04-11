#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    what = what.strip()
    scream = "".join(re.findall(r'\w', what, re.UNICODE))

    if scream.isupper():
      return "Whoa, chill out!"
    elif re.match(r'.*\?$', what):
      return "Sure."
    elif not what:
      return "Fine. Be that way!"
    else:
      return "Whatever."
