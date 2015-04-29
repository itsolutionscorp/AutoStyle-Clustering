import re

def hey(what):
    what = re.sub('\s+$', '', what)

    if re.match('[^a-z]+[!?]?$', what) and re.match('.*[A-Z]', what):
        return 'Whoa, chill out!'
    elif re.match('.*\?$', what):
        return "Sure."
    elif re.match('\s*$', what):
        return 'Fine. Be that way!'
    else:
        return "Whatever."
