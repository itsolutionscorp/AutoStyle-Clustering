#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()


    if not what:
      return "Fine. Be that way!"	

    elif what[-1] == "?" and what.isupper() == False: 
        return "Sure."

    elif what.isupper():
        return "Whoa, chill out!"
    else:
        return "Whatever."
