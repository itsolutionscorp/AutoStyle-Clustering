#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
     if len(what.split())== 0:
          return "Fine. Be that way!"
     elif what == what.upper() and what != what.lower():
          return "Whoa, chill out!"
     elif what.find('?') != -1 and not what[what.find('?')-1].isspace():
          return "Sure."
     else:
          return "Whatever."
