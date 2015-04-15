"""
hey() checks in order to see if a string appears to be shouting
(capitalization), asking (question mark), or silent (empty string) before
returning the appropriate response.
"""

def hey(textString):
    if textString.isupper():
        return 'Woah, chill out!'
    elif textString.endswith('?'):
        return 'Sure.'
    elif not textString.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
