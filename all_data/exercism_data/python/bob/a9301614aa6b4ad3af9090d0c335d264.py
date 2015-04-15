#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if len(what.strip()) == 0:
      return 'Fine. Be that way!'
    elif what == what.upper() and any(c.isalpha() for c in what):
      return 'Whoa, chill out!'
    elif what[-1:] == '?':
      return 'Sure.'

    return 'Whatever.'
