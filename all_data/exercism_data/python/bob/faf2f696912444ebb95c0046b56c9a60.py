import string

def hey(what):
    if not what.strip():
        return "Fine. Be that way!"
    if any(c in string.ascii_letters for c in what) and what.isupper():
        return "Whoa, chill out!"
    if what.endswith("?"):
        return "Sure."
    return "Whatever."
      
