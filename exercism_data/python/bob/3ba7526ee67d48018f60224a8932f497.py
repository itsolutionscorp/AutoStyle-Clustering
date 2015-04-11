import re

def hey(what):
    what = what.strip()
    if not what:
    	return "Fine. Be that way!"
    elif is_upper(what):
    	return "Whoa, chill out!"
    elif what.endswith("?"):
    	return "Sure." 
    else:
    	return "Whatever."
