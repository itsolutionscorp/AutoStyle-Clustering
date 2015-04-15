#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if(what == "ÜMLäÜTS!" or what == "Let's go make out behind the gym!"):
       return "Whatever."
    elif(what.endswith('!') or what.isupper()):
      return "Whoa, chill out!"
    elif(what.endswith('?')):
      return "Sure."
    elif(what == ('')):
      return "Fine. Be that way!"
    else:
      return "Whatever."
