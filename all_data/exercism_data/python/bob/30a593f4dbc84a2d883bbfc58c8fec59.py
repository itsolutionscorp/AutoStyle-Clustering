def hey(what):
    what = what.strip()
    if   __silence(what):  return "Fine. Be that way!"
    elif __shouting(what): return "Whoa, chill out!"
    elif __question(what): return "Sure."
    else:                  return "Whatever."

def __silence(what):  return not what
def __shouting(what): return what.isupper()
def __question(what): return what.endswith("?")
