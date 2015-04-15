import string

def hey(what):
    if not what.strip():
        return "Fine. Be that way!"
    if  what.isupper() and any(c in string.ascii_upppercase for c in what):
        return "Whoa, chill out!"
    if what.endswith("?"):
        return "Sure."
    return "Whatever."
      
