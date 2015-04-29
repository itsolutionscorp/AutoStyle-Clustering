#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
  return "Fine. Be that way!" if len(what.strip()) == 0 else ("Whoa, chill out!" if sum([a.islower() for a in what.strip()]) == 0 and sum([a.isupper() for a in what.strip()]) > 0 else ("Sure." if what.strip()[-1] == '?' else "Whatever."))
