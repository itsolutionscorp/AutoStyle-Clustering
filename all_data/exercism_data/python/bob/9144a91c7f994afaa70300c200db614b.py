import re

def hey(x):
    if (len(x.strip()) > 0):
        if (x.upper() == x and re.search('[a-zA-Z]', x)):
            return "Woah, chill out!"
        elif (x[-1] == '?'):
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"
