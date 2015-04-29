import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Has no alphanumeric chars.
    if not re.search('[a-zA-Z0-9]', what):
      return 'Fine. Be that way!'

    # Has alphabetical chars which are in ALLCAPS.
    elif re.search('[A-Z]', what) and what == what.upper():
      return 'Whoa, chill out!'

    # Is a question?
    elif what.strip().endswith('?'):
      return 'Sure.'

    else:
      return 'Whatever.'
