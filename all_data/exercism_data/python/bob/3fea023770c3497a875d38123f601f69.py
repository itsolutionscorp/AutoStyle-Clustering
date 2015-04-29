#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip() # remove starting/trailing whitespace
    if not what:
      return 'Fine. Be that way!'
    # so we can catch non-ascii characters
    elif what.isupper():
      return 'Whoa, chill out!'
    elif what.endswith('?'):
      return 'Sure.'
    else:
      return 'Whatever.'
