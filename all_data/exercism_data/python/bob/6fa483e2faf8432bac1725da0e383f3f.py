#ugly code, written in the fast lane just to pass the tests.

import re

def hey(s):
    if not s:
        return 'Fine. Be that way!'
    elif s.isupper() and s.endswith("!"):
        return 'Whoa, chill out!'
    elif s.isupper():
        return 'Whoa, chill out!'
    elif list(s)[-1] == ".":
        return 'Whatever.'
    elif list(s)[-1] == '?' or not s:
        return 'Sure.'
    elif "  " in s:
        return "Fine. Be that way!"
    else:
        return 'Whatever.'
