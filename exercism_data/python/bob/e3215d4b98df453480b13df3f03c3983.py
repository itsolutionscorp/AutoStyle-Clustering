import re

def hey(what):

    if re.match( r'\s*$', what ):
        return 'Fine. Be that way!'

    hasLower = any(c.islower() for c in what)
    hasUpper = any(c.isupper() for c in what)
    if not hasLower and hasUpper:
        return 'Whoa, chill out!'

    if re.search( r'\?\s*$', what ):
        return "Sure."

    return "Whatever."
