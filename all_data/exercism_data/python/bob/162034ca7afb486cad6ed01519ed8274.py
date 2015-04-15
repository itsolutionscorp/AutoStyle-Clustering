#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    if what == what.upper() and what != what.lower():
      return "Whoa, chill out!"
    elif what.endswith("?"):
      return "Sure."
    elif what.find(/^\s*$/) != None:
      return "Fine. Be that way!"
    else:
      return "Whatever."
