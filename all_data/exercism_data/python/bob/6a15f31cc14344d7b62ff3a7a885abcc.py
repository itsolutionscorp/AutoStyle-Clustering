import re

def hey(what):

    if re.match( r'\s*$', what ):
        return 'Fine. Be that way!'

    lowercase = [c for c in what if c.islower()]
    uppercase = [c for c in what if c.isupper()]
    if len(lowercase) == 0 and len(uppercase) > 0 :
        return 'Whoa, chill out!'

    if re.search( r'\?\s*$', what ):
        return "Sure."

    return "Whatever."
