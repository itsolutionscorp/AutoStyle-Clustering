#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    import string
    if not what or all(c in string.whitespace for c in what):
      return 'Fine. Be that way!'
    elif str.upper(what) == what and any(c.isalpha() for c in what):
      return 'Whoa, chill out!'
    elif ('?' in what) and (what[-1] == '?' or all(c in string.whitespace for c in what[-''.join(reversed(what)).index('?'):])):
      return 'Sure.'
    else:
      return 'Whatever.'
